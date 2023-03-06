package com.code.dict.protobuf;

import lombok.Data;


/**
 * VehClassDTO
 *
 * pb文件VehClass.proto对应的实体DTO
 *
 * pbDto <-> _pbGeneratedClass <-> pb.proto
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Data
public class VehClassDTO {
    private Integer basicVehicleClass;
    private Integer fuelType;
}
