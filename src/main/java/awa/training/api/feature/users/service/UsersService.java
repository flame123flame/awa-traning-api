package awa.training.api.feature.users.service;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.CommonResponse;
import awa.training.api.exception.HandleException;
import awa.training.api.feature.users.dto.UsersDTO;
import awa.training.api.feature.users.entity.UsersEntity;
import awa.training.api.feature.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Log4j2
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public CommonResponse<Object> register(UsersDTO.RegisterReq req) {
        CommonResponse<Object> response = new CommonResponse<>();

        if (usersRepository.existsByUsername(req.getUsername())) {
            throw new HandleException(ErrorCode.DUPLICATE_USER);
        }

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(req.getUsername());
        usersEntity.setPassword(req.getPassword());
        usersEntity.setFullName(req.getFullName());
        usersEntity.setNickName(req.getNickName());
        usersEntity.setCreated_at(LocalDateTime.now());
        if (req.getAvatar() != null) {
//            byte[] decodedBytes = ReportUtils.decodeFile(req.getAvatar());
//            String formattedDateTime = ReportUtils.generateFormattedDateTime();
//            String fileType = ReportUtils.determineFileType(decodedBytes);
//            GenerateFilePath.FileSave filePath = ReportUtils.generateFilePath(pathFile.getFullPath(), "avatar_user",
//                    formattedDateTime, fileType);
//            try {
//                FileUtils.writeByteArrayToFile(new File(filePath.getFileSaveDevice()), decodedBytes);
//            } catch (IOException e) {
//                throw new HandleException(ErrorCode.UNEXPECTED);
//            }
//            usersEntity.setAvatar(filePath.getFileSaveBase());
        }
        usersRepository.save(usersEntity);

        return response;
    }
}
