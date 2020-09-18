package com.repository;

import com.entity.AliEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName AlibabaRepository
 * @Description TODO
 * @Author qiyuanzhao
 * @Date 2020-09-18
 * @Version 1.0
 **/
public interface AlibabaRepository extends JpaRepository<AliEntity,Long> {


    List<AliEntity> findAliEntitiesByTaskId(Long taskId);

}
