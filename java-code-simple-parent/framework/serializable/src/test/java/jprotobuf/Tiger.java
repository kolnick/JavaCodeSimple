package jprotobuf;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author caochaojie
 * 2023/2/2
 * @description
 */
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = "one.proto", filePackage = "pb.one")
@Data
public class Tiger {
    /** id */
    int id;
    /** 老虎的食物 */
    Food food;
}
