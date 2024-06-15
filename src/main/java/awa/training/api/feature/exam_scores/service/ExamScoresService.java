package awa.training.api.feature.exam_scores.service;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.exam_scores.dto.ExamScoresDTO;
import awa.training.api.feature.exam_scores.entity.ExamScoresEntity;
import awa.training.api.feature.exam_scores.repository.ExamScoresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamScoresService {

    private final ExamScoresRepository examScoresRepository;

    @Transactional
    public CommonResponse<Object> create(ExamScoresDTO.CreateExamScoresReq req) {
        CommonResponse<Object> response = new CommonResponse<>();

        ExamScoresEntity examScoresEntity = new ExamScoresEntity();
        examScoresEntity.setEnrollmentId(req.getEnrollmentId());
        examScoresEntity.setMidtermScore(req.getMidtermScore());
        examScoresEntity.setFinalScore(req.getFinalScore());
        examScoresEntity.setSumScore(req.getSumScore());
        examScoresEntity.setSemester(req.getSemester());
        examScoresEntity.setCreateBy("System");
        examScoresEntity.setCreateDate(LocalDateTime.now());
        examScoresRepository.save(examScoresEntity);

        response.setMessage("ExamScores created successfully");
        return response;
    }

    @Transactional
    public CommonResponse<ExamScoresDTO.UpdateExamScoresRes> update(ExamScoresDTO.UpdateExamScoresRes req) {
        CommonResponse<ExamScoresDTO.UpdateExamScoresRes> response = new CommonResponse<>();
        Optional<ExamScoresEntity> examScoresOptional = examScoresRepository.findById(req.getId());
        ExamScoresEntity examScore = examScoresOptional.get();

        examScore.setEnrollmentId(req.getEnrollmentId());
        examScore.setMidtermScore(req.getMidtermScore());
        examScore.setFinalScore(req.getFinalScore());
        examScore.setSumScore(req.getSumScore());
        examScore.setSemester(req.getSemester());
        examScore.setUpdateBy("System");
        examScore.setUpdateDate(LocalDateTime.now());

        examScoresRepository.save(examScore);

        return response;
    }

    @Transactional
    public CommonResponse<List<ExamScoresDTO.FindAllExamScoresRes>> findAll() {
        CommonResponse<List<ExamScoresDTO.FindAllExamScoresRes>> response = new CommonResponse<>();
        List<ExamScoresDTO.FindAllExamScoresRes> dataReturn = new ArrayList<>();
        List<ExamScoresEntity> examScores = examScoresRepository.findAll();

        for (int i = 0; i < examScores.size(); i++) {
            ExamScoresDTO.FindAllExamScoresRes data = new ExamScoresDTO.FindAllExamScoresRes();
            data.setId(examScores.get(i).getId());
            data.setEnrollmentId(examScores.get(i).getEnrollmentId());
            data.setMidtermScore(examScores.get(i).getMidtermScore());
            data.setFinalScore(examScores.get(i).getFinalScore());
            data.setSumScore(examScores.get(i).getSumScore());
            data.setSemester(examScores.get(i).getSemester());
            data.setCreateBy(examScores.get(i).getCreateBy());
            data.setUpdateBy(examScores.get(i).getUpdateBy());

            dataReturn.add(data);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<ExamScoresDTO.FindAllExamScoresRes> findById(Long id) {
        CommonResponse<ExamScoresDTO.FindAllExamScoresRes> response = new CommonResponse<>();
        Optional<ExamScoresEntity> examScoresOptional = examScoresRepository.findById(id);

        if (examScoresOptional.isPresent()) {
            ExamScoresEntity examScore = examScoresOptional.get();
            ExamScoresDTO.FindAllExamScoresRes data = new ExamScoresDTO.FindAllExamScoresRes();
            data.setId(examScore.getId());
            data.setEnrollmentId(examScore.getEnrollmentId());
            data.setMidtermScore(examScore.getMidtermScore());
            data.setFinalScore(examScore.getFinalScore());
            data.setSumScore(examScore.getSumScore());
            data.setSemester(examScore.getSemester());
            data.setCreateBy(examScore.getCreateBy());
            data.setUpdateBy(examScore.getUpdateBy());

            response.setData(data);
        }

        return response;
    }

    @Transactional
    public CommonResponse<ExamScoresDTO.DeleteExamScoresReq> delete(ExamScoresDTO.DeleteExamScoresReq id) {
        CommonResponse<ExamScoresDTO.DeleteExamScoresReq> response = new CommonResponse<>();
        examScoresRepository.delete(id.getId());

        return response;
    }
}
