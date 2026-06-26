package com.example.hdld.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates platform-style transaction ids (ma_giao_dich) of the form
 * {@code <prefix>_<yyyyMMddHHmmss>_<rand6>}, e.g. {@code insert_20250610071354_5wrSp1}.
 */
public final class TransactionIdGenerator {

    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final String ALPHANUM =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /** Prefixes used by the platform for each kind of transaction. */
    public static final String INSERT = "insert";
    public static final String INSERT_BATCH = "insertlo";
    public static final String UPDATE = "update";
    public static final String UPLOAD = "upload";
    public static final String DELETE_FILE = "deletefile";
    public static final String CHECK = "checktrans";

    private TransactionIdGenerator() {
    }

    public static String generate(String prefix) {
        return prefix + "_" + LocalDateTime.now().format(TS) + "_" + randomSuffix();
    }

    private static String randomSuffix() {
        StringBuilder sb = new StringBuilder(6);
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUM.charAt(rnd.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}
