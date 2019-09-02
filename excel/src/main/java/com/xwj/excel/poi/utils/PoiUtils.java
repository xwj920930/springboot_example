package com.xwj.excel.poi.utils;

import com.xwj.excel.poi.annotation.ExcelIgnore;
import com.xwj.excel.poi.enumm.PoiType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Excel操作工具类.
 *
 * @author xuwenjie
 * @version  1.0
 */
public class PoiUtils {
    private static final Logger log = LoggerFactory.getLogger(PoiUtils.class);

    private static final int DEFAULT_SHEET_PAGE=0;
    private static final int DEFAULT_CELL_START_INDEX=0;

    private PoiUtils(){}

    /**
     * Excel文件解析为实体列表.
     *
     * @param inputStream 文件输入流
     * @param poiType Excel类型
     * @param clazz 实体的类型
     * @param <T> 泛型
     * @return 实体类列表
     */
    public static <T> List<T> excelToEntities(InputStream inputStream, PoiType poiType, Class<T> clazz) {
        return excelToEntities(inputStream, poiType, DEFAULT_SHEET_PAGE, clazz);
    }

    /**
     * Excel文件解析为实体列表.
     *
     * @param inputStream 文件输入流
     * @param poiType Excel类型
     * @param sheetPage sheet页码
     * @param clazz 实体的类型
     * @param <T> 泛型
     * @return 实体类列表
     */
    public static <T> List<T> excelToEntities(InputStream inputStream, PoiType poiType, int sheetPage, Class<T> clazz) {
        return sheetToEntities(getPoiSheet(inputStream, poiType, sheetPage), clazz);
    }

    /**
     * 根据输入流返回特定的poiWorkbook.
     *
     * @param inputStream 输入流
     * @param poiType Excel类型
     * @return 特定的poiWorkbook
     */
    private static Workbook getPoiWorkbook(InputStream inputStream, PoiType poiType){
        Workbook workbook;
        try {
            if (poiType==PoiType.XLSX){
                workbook = new XSSFWorkbook(inputStream);
            }else {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.error("PoiUtils.getPoiWorkbook:读取文件路径出错");
            return null;
        }
        return workbook;
    }

    /**
     * 根据输入流返回特定的poiSheet.
     *
     * @param inputStream 输入流
     * @param poiType Excel类型
     * @param sheetPage sheet页
     * @return sheet
     */
    private static Sheet getPoiSheet(InputStream inputStream, PoiType poiType, Integer sheetPage){
        return Objects.requireNonNull(PoiUtils.getPoiWorkbook(inputStream, poiType)).getSheetAt(sheetPage);
    }

    /**
     * 将某一行数据转换为一个实体类.
     *
     * @param row 行
     * @param clazz 类型
     * @param <T> 泛型
     * @return 实体类，当为空行是返回null
     */
    private static <T> T rowToEntity(Row row, Class<T> clazz) {
        //空行或者字段为空
        if (null==row||row.getCell(DEFAULT_CELL_START_INDEX).getCellType()== Cell.CELL_TYPE_BLANK){
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        Constructor<T> constructor;
        T t;
        try {
            constructor = clazz.getConstructor();
            t = constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            log.error("PoiUtils.rowToEntity:实体类反射出错");
            return null;
        }
        int index=0;
        String type;
        for (Field field : fields) {
            //isSynthetic()主要用于jacoco报错
            if (field.isAnnotationPresent(ExcelIgnore.class)||field.isSynthetic()){
                continue;
            }
            field.setAccessible(true);
            type = field.getType().toString();
            try {
                switch (type){
                    case "class java.lang.Integer":
                        field.set(t, (int) row.getCell(index).getNumericCellValue());
                        break;
                    case "class java.time.LocalDate":
                        field.set(t, row.getCell(index).getDateCellValue()
                                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        break;
                    default:
                        field.set(t, row.getCell(index).getStringCellValue());
                        break;
                }
            } catch (IllegalAccessException e){
                log.error("PoiUtils.rowToEntity:Excel文件解析出错");
                return null;
            }
            index++;
        }
        return t;
    }

    /**
     * 根据sheet解析实体类列表.
     *
     * @param sheet sheet
     * @param clazz 类型
     * @param <T> 泛型
     * @return 实体类列表
     */
    private static <T> List<T> sheetToEntities(Sheet sheet, Class<T> clazz) {
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        List<T> ts=new ArrayList<>();
        for (int i=firstRowNum+1; i<lastRowNum; i++){
            T t = rowToEntity(sheet.getRow(i), clazz);
            if (null != t){
                ts.add(t);
            }
        }
        return ts;
    }
}
