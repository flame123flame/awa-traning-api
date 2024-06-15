package awa.training.api.feature.university.service;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.CommonResponse;
import awa.training.api.exception.HandleException;
import awa.training.api.feature.university.dto.UniversityDTO;
import awa.training.api.feature.university.entity.UniversityEntity;
import awa.training.api.feature.university.repository.UniversityRepository;

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
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Transactional
    public CommonResponse<Object> create(UniversityDTO.CreateUniversityReq req) {
        CommonResponse<Object> response = new CommonResponse<>();
//        if (universityRepository.existsByUniversityName(req.getUniversityName())) {
//            throw new HandleException(ErrorCode.DUPLICATE_USER);
//        }

        UniversityEntity universityEntity = new UniversityEntity();
        universityEntity.setUniversityName(req.getUniversityName());
        universityEntity.setUniversityNameEn(req.getUniversityNameEn());
        universityEntity.setUniversityShortName(req.getUniversityShortName());
        universityEntity.setUniversityCode(req.getUniversityCode());
        universityEntity.setCreateBy("System");
        universityEntity.setCreateDate(LocalDateTime.now());
        universityRepository.save(universityEntity);

        response.setMessage("University created successfully");
        return response;
    }

    @Transactional
    public CommonResponse<UniversityDTO.UpdateUniversityRes> update(UniversityDTO.UpdateUniversityRes id) {
        CommonResponse<UniversityDTO.UpdateUniversityRes> response = new CommonResponse<>();
        Optional<UniversityEntity> universityOptional = universityRepository.update(id.getId());
        UniversityEntity university = universityOptional.get();

        university.setUniversityName(id.getUniversityName());
        university.setUniversityNameEn(id.getUniversityNameEn());
        university.setUniversityShortName(id.getUniversityShortName());
        university.setUniversityCode(id.getUniversityCode());
        university.setUpdateBy("System");
        university.setUpdateDate(LocalDateTime.now());

        universityRepository.save(university);

        return response;
    }
        @Transactional
        public CommonResponse<List<UniversityDTO.FindAllUniversityRes>> findAll() {
            CommonResponse<List<UniversityDTO.FindAllUniversityRes>> response = new CommonResponse<>();
            List<UniversityDTO.FindAllUniversityRes> dataReturn = new ArrayList<>();
            List<UniversityEntity>  getFindAllUniversity = universityRepository.getAll();

            for (int i = 0; i < getFindAllUniversity.size(); i++) {
                UniversityDTO.FindAllUniversityRes dataSet = new UniversityDTO.FindAllUniversityRes();
                dataSet.setId(getFindAllUniversity.get(i).getId());
                dataSet.setUniversityName(getFindAllUniversity.get(i).getUniversityName());
                dataSet.setUniversityNameEn(getFindAllUniversity.get(i).getUniversityNameEn());
                dataSet.setUniversityShortName(getFindAllUniversity.get(i).getUniversityShortName());
                dataSet.setUniversityCode(getFindAllUniversity.get(i).getUniversityCode());

                dataReturn.add(dataSet);
            }

            response.setData(dataReturn);
            return response;
        }

        @Transactional
        public CommonResponse<UniversityDTO.FindAllUniversityRes> findById(Long id) {
            CommonResponse<UniversityDTO.FindAllUniversityRes> response = new CommonResponse<>();
            Optional<UniversityEntity> userOptional = universityRepository.update(id);
            UniversityEntity universityById = userOptional.get();
            UniversityDTO.FindAllUniversityRes data = new UniversityDTO.FindAllUniversityRes();
            data.setId(universityById.getId());
            data.setUniversityName(universityById.getUniversityName());
            data.setUniversityNameEn(universityById.getUniversityNameEn());
            data.setUniversityShortName(universityById.getUniversityShortName());
            data.setUniversityCode(universityById.getUniversityCode());

            response.setData(data);


            return response;
        }

        @Transactional
        public CommonResponse<UniversityDTO.DeleteUniversityReq> delete(UniversityDTO.DeleteUniversityReq id) {
            CommonResponse<UniversityDTO.DeleteUniversityReq> response = new CommonResponse<>();
            universityRepository.delete(id.getId());

            return response;
        }

}