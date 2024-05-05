package top.help.pal.common.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private T data;

    private String msg;

    private Integer code;

    public static <T> Result<T> success(T data) {
        return new Result<>(data, "success", 200);
    }
}
