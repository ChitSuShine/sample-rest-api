package com.api.sample.domain.task;

import com.api.sample.common.ErrorCodeEnum;
import com.api.sample.common.StatusCodeEnum;
import com.api.sample.exception.BusinessException;
import com.api.sample.model.command.UpdateTaskCommand;
import com.api.sample.model.response.StandardResponse;
import com.api.sample.service.BaseService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateTaskService implements
    BaseService<UpdateTaskCommand, StandardResponse<Null>> {

  private final TaskRepository taskRepository;

  public UpdateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public StandardResponse<Null> execute(UpdateTaskCommand input) {
    Task task = taskRepository.findById(input.id())
        .orElseThrow(() -> BusinessException.fromErrorCode(HttpStatus.NOT_FOUND,
            ErrorCodeEnum.ERR_1000_TASK_NOT_FOUND, null));
    task.setTitle(input.title());
    task.setDescription(input.description());
    taskRepository.save(task);
    return StandardResponse.fromCodeEnum(StatusCodeEnum.STA_5000_SUCCESS);
  }
}
