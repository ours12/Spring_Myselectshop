package com.example.myselectshop.models;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass // 멤버변수가 컬럼이 되도록 한다.
@EntityListeners(AuditingEntityListener.class)  // 변경되었을때 자동으로 기록한다.
public abstract class Timestamped {

    @CreatedDate  // 최초 생성 시점
    private LocalDateTime createAt;

    @LastModifiedDate  // 마지막 변경 시점
    private LocalDateTime modifiedAt;


}
