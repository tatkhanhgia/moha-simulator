package com.example.hdld.infrastructure.config;

import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.application.port.PasswordEncoderPort;
import com.example.hdld.application.port.TokenPort;
import com.example.hdld.application.usecase.AuthenticateUseCase;
import com.example.hdld.application.usecase.BulkCreateContractUseCase;
import com.example.hdld.application.usecase.ChangePasswordUseCase;
import com.example.hdld.application.usecase.CheckTransactionUseCase;
import com.example.hdld.application.usecase.CreateContractUseCase;
import com.example.hdld.application.usecase.CreateEnterpriseUseCase;
import com.example.hdld.application.usecase.DeleteAttachmentUseCase;
import com.example.hdld.application.usecase.GetContractUseCase;
import com.example.hdld.application.usecase.ListBusinessSectorsUseCase;
import com.example.hdld.application.usecase.ListEnterpriseTypesUseCase;
import com.example.hdld.application.usecase.ListIndustriesUseCase;
import com.example.hdld.application.usecase.ListProvincesUseCase;
import com.example.hdld.application.usecase.ListWardsUseCase;
import com.example.hdld.application.usecase.UpdateContractUseCase;
import com.example.hdld.application.usecase.UpdateEnterpriseUseCase;
import com.example.hdld.application.usecase.UploadAttachmentUseCase;
import com.example.hdld.domain.repository.AttachmentRepository;
import com.example.hdld.domain.repository.BusinessSectorRepository;
import com.example.hdld.domain.repository.EnterpriseRepository;
import com.example.hdld.domain.repository.EnterpriseTypeRepository;
import com.example.hdld.domain.repository.IndustryRepository;
import com.example.hdld.domain.repository.LaborContractRepository;
import com.example.hdld.domain.repository.ProvinceRepository;
import com.example.hdld.domain.repository.TransactionRepository;
import com.example.hdld.domain.repository.UserRepository;
import com.example.hdld.domain.repository.WardRepository;
import com.example.hdld.domain.service.ContractDomainService;
import com.example.hdld.domain.service.EnterpriseDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration that wires all use cases as beans.
 */
@Configuration
public class UseCaseConfig {

    @Bean
    public AuthenticateUseCase authenticateUseCase(UserRepository userRepository,
                                                    PasswordEncoderPort passwordEncoderPort,
                                                    TokenPort tokenPort) {
        return new AuthenticateUseCase(userRepository, passwordEncoderPort, tokenPort);
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(UserRepository userRepository,
                                                       PasswordEncoderPort passwordEncoderPort,
                                                       TokenPort tokenPort) {
        return new ChangePasswordUseCase(userRepository, passwordEncoderPort, tokenPort);
    }

    @Bean
    public EnterpriseDomainService enterpriseDomainService(EnterpriseRepository enterpriseRepository) {
        return new EnterpriseDomainService(enterpriseRepository);
    }

    @Bean
    public CreateEnterpriseUseCase createEnterpriseUseCase(EnterpriseRepository enterpriseRepository,
                                                           EnterpriseDomainService enterpriseDomainService) {
        return new CreateEnterpriseUseCase(enterpriseRepository, enterpriseDomainService);
    }

    @Bean
    public UpdateEnterpriseUseCase updateEnterpriseUseCase(EnterpriseRepository enterpriseRepository) {
        return new UpdateEnterpriseUseCase(enterpriseRepository);
    }

    @Bean
    public ContractDomainService contractDomainService() {
        return new ContractDomainService();
    }

    @Bean
    public CreateContractUseCase createContractUseCase(LaborContractRepository contractRepository,
                                                       EnterpriseRepository enterpriseRepository,
                                                       ContractDomainService contractDomainService,
                                                       TransactionRepository transactionRepository) {
        return new CreateContractUseCase(contractRepository, enterpriseRepository,
                contractDomainService, transactionRepository);
    }

    @Bean
    public BulkCreateContractUseCase bulkCreateContractUseCase(CreateContractUseCase createContractUseCase) {
        return new BulkCreateContractUseCase(createContractUseCase);
    }

    @Bean
    public UpdateContractUseCase updateContractUseCase(LaborContractRepository contractRepository,
                                                       TransactionRepository transactionRepository) {
        return new UpdateContractUseCase(contractRepository, transactionRepository);
    }

    @Bean
    public GetContractUseCase getContractUseCase(LaborContractRepository contractRepository) {
        return new GetContractUseCase(contractRepository);
    }

    @Bean
    public UploadAttachmentUseCase uploadAttachmentUseCase(AttachmentRepository attachmentRepository,
                                                           LaborContractRepository contractRepository,
                                                           FileStoragePort fileStoragePort,
                                                           TransactionRepository transactionRepository) {
        return new UploadAttachmentUseCase(attachmentRepository, contractRepository,
                fileStoragePort, transactionRepository);
    }

    @Bean
    public DeleteAttachmentUseCase deleteAttachmentUseCase(AttachmentRepository attachmentRepository,
                                                           FileStoragePort fileStoragePort,
                                                           TransactionRepository transactionRepository) {
        return new DeleteAttachmentUseCase(attachmentRepository, fileStoragePort, transactionRepository);
    }

    @Bean
    public CheckTransactionUseCase checkTransactionUseCase(TransactionRepository transactionRepository) {
        return new CheckTransactionUseCase(transactionRepository);
    }

    @Bean
    public ListProvincesUseCase listProvincesUseCase(ProvinceRepository provinceRepository) {
        return new ListProvincesUseCase(provinceRepository);
    }

    @Bean
    public ListWardsUseCase listWardsUseCase(WardRepository wardRepository) {
        return new ListWardsUseCase(wardRepository);
    }

    @Bean
    public ListBusinessSectorsUseCase listBusinessSectorsUseCase(BusinessSectorRepository businessSectorRepository) {
        return new ListBusinessSectorsUseCase(businessSectorRepository);
    }

    @Bean
    public ListIndustriesUseCase listIndustriesUseCase(IndustryRepository industryRepository) {
        return new ListIndustriesUseCase(industryRepository);
    }

    @Bean
    public ListEnterpriseTypesUseCase listEnterpriseTypesUseCase(EnterpriseTypeRepository enterpriseTypeRepository) {
        return new ListEnterpriseTypesUseCase(enterpriseTypeRepository);
    }
}
