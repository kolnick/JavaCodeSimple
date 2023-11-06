package com.caochaojie;

/**
 * @author caochaojie
 * @Date 2022/8/19
 */
public class EasyExcel2Test {

/*
    @Test
    public void test() {
        String path = FilePathUtil.getConfigPath("026激活码.xlsx").getPath();
        ExcelReaderBuilder read = EasyExcel.read(path);
        ExcelReader build = read.build();
        ExcelExecutor excelExecutor = build.excelExecutor();
        List<ReadSheet> readSheets = excelExecutor.sheetList();
        for (ReadSheet readSheet : readSheets) {
            String sheetName = readSheet.getSheetName();
            Integer sheetNo = readSheet.getSheetNo();
            List<Object> dataList = read.sheet(sheetNo).headRowNumber(0).doReadSync();
            // 获取前6行数据
            if (dataList.isEmpty()) {
                System.out.println("没有任何数据");
                continue;
            }

            if (dataList.size() <= 6) {
                System.out.println("跳过行数不正确");
            }

            Map<Integer, String> titleDesc = (HashMap) dataList.get(0);
            Map<Integer, String> emptyCheck = (HashMap) dataList.get(1);
            Map<Integer, String> varMap = (HashMap) dataList.get(2);
            Map<Integer, String> typeMap = (HashMap) dataList.get(3);
            Map<Integer, String> scopeMap = (HashMap) dataList.get(4);
            if (scopeMap != null) {
                for (Map.Entry<Integer, String> entry : scopeMap.entrySet()) {
                    Integer number = entry.getKey();
                    String scope = entry.getValue();
                    if (!scope.contains("cs") && !scope.contains("sc") && !scope.contains("s")) {
                        continue;
                    }
                    String title = titleDesc.get(number);
                    String empty = emptyCheck.get(number);
                    String var = varMap.get(number);
                    String type = typeMap.get(number);
                    com.caochaojie.model.JavaModel javaModel = new com.caochaojie.model.JavaModel(var, title, type, BooleanUtil.toBoolean(empty));
                    System.out.println(javaModel);
                    // 生成Java代码
                }
            }
        }
    }*/
}
