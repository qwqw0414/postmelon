package com.joje.postmelon.model.vo;

import com.joje.postmelon.common.constants.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {
    private String code = StatusType.SUCCESS.getCode();
    private String message = StatusType.SUCCESS.getMessage();
    private Boolean status = true;
    private Map<String, Object> data = new HashMap<>();

    public ResultVo(StatusType statusType) {
        this.code = statusType.getCode();
        this.message = statusType.getMessage();
        this.status = StatusType.SUCCESS.equals(statusType);
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

}
