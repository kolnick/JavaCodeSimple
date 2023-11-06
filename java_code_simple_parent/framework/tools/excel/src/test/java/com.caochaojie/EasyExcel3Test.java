package com.caochaojie;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.caochaojie.model.JavaModel;
import org.apache.logging.log4j.util.Strings;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caochaojie
 * @Date 2022/8/19
 */
public class EasyExcel3Test {

    class HeadReadListener extends AnalysisEventListener<Map<Integer, String>> {
        private static final int LIMIT_ROWS = 6;
        //Excel数据缓存结构
        private List<Map<Integer, String>> list = new ArrayList<>();
        private Map<String, JavaModel> columnMap = new HashMap<>();

        private int rows = 0;

        private boolean readHeadComplete;

        @Override
        public void invoke(Map<Integer, String> map, AnalysisContext analysisContext) {

            if (rows < LIMIT_ROWS) {
                readHeadData(map);
            } else {
                if (!readHeadComplete) {
                    headComplete();
                    readHeadComplete = true;
                    System.out.println(columnMap);
                }
                readData(map);
            }
            rows++;
        }

        private void headComplete() {
            Map<Integer, String> columnComments = list.get(0);
            Map<Integer, String> nullEmpty = list.get(1);
            Map<Integer, String> varNames = list.get(2);
            Map<Integer, String> types = list.get(3);
            Map<Integer, String> namespace = list.get(4);
            for (Map.Entry<Integer, String> entry : columnComments.entrySet()) {
                Integer column = entry.getKey();
                if (column > 0) {
                    String isNull = nullEmpty.get(column);
                    String varName = varNames.get(column);
                    String type = types.get(column);
                    String cs = namespace.get(column);
                    String columnComment = entry.getValue();
                    if (Strings.isNotBlank(cs) && cs.contains("s")) {
                        JavaModel javaModel = new JavaModel(column, varName, columnComment, type, BooleanUtil.toBoolean(isNull));
                        columnMap.put(varName, javaModel);
                    }
                }

            }
        }

        private void readData(Map<Integer, String> map) {
            // 一行的数据
        }

        private void readHeadData(Map<Integer, String> map) {
            list.add(map);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }

        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            System.out.println("headmap:" + headMap);
        }


    }

    @Test
    public void test() {
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("026激活码.xlsx");
        HeadReadListener listener = new HeadReadListener();
        EasyExcel.read(resourceAsStream, listener).headRowNumber(0).sheet().doRead();
    }
}
