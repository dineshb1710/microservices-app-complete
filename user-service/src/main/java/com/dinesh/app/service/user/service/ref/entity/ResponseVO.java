package com.dinesh.app.service.user.service.ref.entity;

import com.dinesh.app.service.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {

    private User user;
    private Department department;
}
