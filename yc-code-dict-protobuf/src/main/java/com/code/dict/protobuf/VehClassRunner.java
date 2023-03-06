package com.code.dict.protobuf;

import com.code.dict.protobuf.pb.v1._VehClass;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * VehClassRunner
 *
 * 这里demo一下
 *  1、DTO转PB
 *  2、PB转Base64
 *  3、Base64转JSON
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class VehClassRunner {

    public static void main(String[] args) {
        /* dto set */
        VehClassDTO dto = new VehClassDTO();
        dto.setBasicVehicleClass(4);
        dto.setFuelType(4);
        /* build */
        _VehClass.VehicleClassification _VehicleClassification = build(dto);
        /* pb -> base64 */
        String base64Str = trans2Base64(_VehicleClassification);
        System.out.println(base64Str);
        /* base64 -> json */
        String json = base642Json(base64Str, true, true);
        System.out.println(json);
    }


    public static String trans2Base64(_VehClass.VehicleClassification _VehicleClassification) {
        byte[] bytes = _VehicleClassification.toByteArray();
        byte[] encodeBytes = Base64.getEncoder().encode(bytes);
        return new String(encodeBytes, StandardCharsets.UTF_8);
    }

    public static String base642Json(String base64Str, Boolean showDefault, Boolean changeEnum) {
        byte[] decode = Base64.getDecoder().decode(base64Str);
        try {
            _VehClass.VehicleClassification _VehicleClassification = _VehClass.VehicleClassification.parseFrom(decode);
            JsonFormat.Printer printer = JsonFormat.printer();
            if (showDefault) {
                printer = printer.includingDefaultValueFields();
            }
            if (changeEnum) {
                printer = printer.printingEnumsAsInts();
            }
            return printer.print(_VehicleClassification);
        } catch (InvalidProtocolBufferException e) {
            return "格式错误";
        }
    }

    public static _VehClass.VehicleClassification build(VehClassDTO dto) {
        return _VehClass.VehicleClassification.newBuilder()
                .setFuelTypeValue(dto.getFuelType())
                .setClassificationValue(dto.getBasicVehicleClass())
                .build();
    }
}
