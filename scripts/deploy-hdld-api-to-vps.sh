#!/usr/bin/env bash
#
# Deploy HDLD API to a VPS using Docker Compose (production profile).
# Target: Linux VPS with Docker Engine + Docker Compose plugin installed.
#
# Usage:
#   export JWT_SECRET="your-256-bit-base64-secret"
#   ./scripts/deploy-hdld-api-to-vps.sh
#
# Or run directly on the VPS inside the project root.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
COMPOSE_FILE="${PROJECT_ROOT}/docker-compose.prod.yml"
SERVICE_NAME="hdld-api"
CONTAINER_NAME="hdld-api"
PORT="8080"

cd "${PROJECT_ROOT}"

# ---------------------------------------------------------------------------
# 1. Preflight checks
# ---------------------------------------------------------------------------
echo "==> Checking prerequisites..."

if ! command -v docker &>/dev/null; then
    echo "ERROR: docker is not installed."
    exit 1
fi

if ! docker compose version &>/dev/null && ! docker-compose version &>/dev/null; then
    echo "ERROR: docker compose plugin is not installed."
    exit 1
fi

if [[ ! -f "${COMPOSE_FILE}" ]]; then
    echo "ERROR: ${COMPOSE_FILE} not found. Are you running this from the project root?"
    exit 1
fi

# ---------------------------------------------------------------------------
# 2. Validate JWT secret
# ---------------------------------------------------------------------------
if [[ -z "${JWT_SECRET:-}" ]]; then
    echo "ERROR: JWT_SECRET environment variable is not set."
    echo "Generate one with:"
    echo "  export JWT_SECRET=\$(openssl rand -base64 32)"
    exit 1
fi

SECRET_LEN="${#JWT_SECRET}"
if [[ ${SECRET_LEN} -lt 32 ]]; then
    echo "WARNING: JWT_SECRET is only ${SECRET_LEN} chars. Recommended minimum 32 chars (256-bit when base64-decoded)."
fi

# ---------------------------------------------------------------------------
# 3. Prepare persistent data directory
# ---------------------------------------------------------------------------
mkdir -p "${PROJECT_ROOT}/data"

# ---------------------------------------------------------------------------
# 4. Build and start services
# ---------------------------------------------------------------------------
echo "==> Building and starting ${SERVICE_NAME}..."

if docker compose version &>/dev/null; then
    docker compose -f "${COMPOSE_FILE}" up -d --build
else
    docker-compose -f "${COMPOSE_FILE}" up -d --build
fi

# ---------------------------------------------------------------------------
# 5. Wait for service health
# ---------------------------------------------------------------------------
echo "==> Waiting for ${SERVICE_NAME} to become ready..."
MAX_RETRIES=30
RETRY_INTERVAL=2
READY=false

for ((i=1; i<=MAX_RETRIES; i++)); do
    HTTP_CODE=$(docker exec "${CONTAINER_NAME}" \
        sh -c "wget -qO- --server-response http://localhost:${PORT}/swagger-ui/index.html 2>&1 | awk '/HTTP/{print \$2}'" || true)

    if [[ "${HTTP_CODE:-}" == "200" ]]; then
        READY=true
        break
    fi

    echo "    Attempt ${i}/${MAX_RETRIES}: not ready yet..."
    sleep "${RETRY_INTERVAL}"
done

if [[ "${READY}" != "true" ]]; then
    echo "ERROR: ${SERVICE_NAME} did not become ready within $((MAX_RETRIES * RETRY_INTERVAL)) seconds."
    echo "Container logs:"
    docker logs --tail 50 "${CONTAINER_NAME}"
    exit 1
fi

# ---------------------------------------------------------------------------
# 6. Summary
# ---------------------------------------------------------------------------
echo ""
echo "========================================================"
echo "  HDLD API deployed successfully!"
echo "========================================================"
echo "  Swagger UI: http://<vps-ip>:${PORT}/swagger-ui/index.html"
echo "  API Base:   http://<vps-ip>:${PORT}/hdld"
echo "  Container:  ${CONTAINER_NAME}"
echo "  Data dir:   ${PROJECT_ROOT}/data"
echo "========================================================"
echo ""
echo "Useful commands:"
echo "  docker logs -f ${CONTAINER_NAME}"
echo "  docker exec -it ${CONTAINER_NAME} sh"
echo "  docker compose -f ${COMPOSE_FILE} down"
