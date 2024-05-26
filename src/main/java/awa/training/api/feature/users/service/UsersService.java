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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        usersRepository.save(usersEntity);

        return response;
    }

    @Transactional
    public CommonResponse<List<UsersDTO.FindAllUserRes>> findAllUser() {
        CommonResponse<List<UsersDTO.FindAllUserRes>> response = new CommonResponse<>();
        List<UsersDTO.FindAllUserRes> dataReturn = new ArrayList<>();
        List<UsersEntity>  getFindAllUser = usersRepository.getAll();

        for (int i = 0; i < getFindAllUser.size(); i++) {
            UsersDTO.FindAllUserRes dataSet = new UsersDTO.FindAllUserRes();
            dataSet.setId(getFindAllUser.get(i).getId());
            dataSet.setUsername(getFindAllUser.get(i).getFullName());
            dataSet.setFullName(getFindAllUser.get(i).getFullName());
            dataSet.setNickName(getFindAllUser.get(i).getNickName());
            dataSet.setAvatar(getFindAllUser.get(i).getAvatar());

            dataReturn.add(dataSet);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<UsersDTO.FindAllUserRes> findByIdUser(Long id) {
        CommonResponse<UsersDTO.FindAllUserRes> response = new CommonResponse<>();
        Optional<UsersEntity> userOptional = usersRepository.findById(id);
            UsersEntity userById = userOptional.get();
            UsersDTO.FindAllUserRes data = new UsersDTO.FindAllUserRes();
            data.setId(userById.getId());
            data.setUsername(userById.getUsername());
            data.setFullName(userById.getFullName());
            data.setNickName(userById.getNickName());
            data.setAvatar(userById.getAvatar());

            response.setData(data);


        return response;
    }


    @Transactional
    public CommonResponse<UsersDTO.DeleteUserRes> deleteUser(Long id) {
        CommonResponse<UsersDTO.DeleteUserRes> response = new CommonResponse<>();

        Optional<UsersEntity> userOptional = usersRepository.deleteUser(id);
        if (!userOptional.isPresent()) {
            throw new HandleException(ErrorCode.DUPLICATE_USER);
        }
        usersRepository.deleteUser(id);

        UsersDTO.DeleteUserRes responseData = new UsersDTO.DeleteUserRes();
        responseData.setId(id);

        response.setData(responseData);
        return response;
    }

    // @Transactional
    // public CommonResponse<UsersDTO.UpdateUserRes> updateUser(Long id, UsersDTO.RegisterReq req) {
    //     CommonResponse<UsersDTO.UpdateUserRes> response = new CommonResponse<>();

    //     Optional<UsersEntity> userOptional = usersRepository.updateUser(id);
    //     UsersEntity user = userOptional.get();
    //     //UsersEntity user = new UsersEntity();
    //     user.setUsername(req.getUsername());
    //     user.setPassword(req.getPassword());
    //     user.setFullName(req.getFullName());
    //     user.setNickName(req.getNickName());
    //     user.setAvatar(req.getAvatar());
    //     user.setCreated_at(LocalDateTime.now());
    //     usersRepository.save(user);

    //     UsersDTO.UpdateUserRes update = new UsersDTO.UpdateUserRes();
    //     update.setUsername(req.getUsername());
    //     update.setPassword(req.getPassword());
    //     update.setFullName(req.getFullName());
    //     update.setNickName(req.getNickName());
    //     update.setCreated_at(LocalDateTime.now());
    //     response.setData(update);



    //     return response;
    // }

}
