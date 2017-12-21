package org.gloria.http;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * http请求返回值封装
 * 
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2017/12/18 15:28   gloria     1.0    	初始化创建<br>
 * </p>
 *
 * @author gloria
 * @version 1.0
 * @since JDK1.8
 */
@Getter
@Setter
public class ResponseBody {

    private String body;
    private Integer code;
    
}
