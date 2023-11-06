package jprotobuf;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

/**
 * @author caochaojie
 * 2023/2/2
 * @description
 */
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "one.proto", filePackage = "pb.one")
@Data
public class Cat {
    /** id */
    int id;
    /** 猫的名字 */
    String catName;
    /** 食物 map */
    Map<Integer, Integer> foodMap;
    /** 道具 id 列表 */
    List<Long> propIdList;
}
