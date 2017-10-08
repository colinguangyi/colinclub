package com.colin.web.service;

import com.colin.web.dao.UserDao;
import com.colin.web.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author zhaolz
 * @create 2017-09-07
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 查询所有用户
     * @return 所有用户集合
     */
    public List<User> getAll(){
        return (List<User>)userDao.findAll();
    }

    /**
     * 添加用户
     * @param user 待添加用户信息
     * @return 已添加用户的ID
     */
    @Transactional
    public Long add(User user){
        User userNew = userDao.save(user);
        return userNew.getId();
    }

    /**
     * 将excel中的数据导入数据库
     * @param filePath excel文件路径
     * @throws Exception 异常信息
     */
    @Transactional
    public void import2DB(String filePath) throws Exception{
        //创建Excel工作簿文件的引用
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));//2007之前的excel版本
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));//2007之后的excel版本
        //在Excel文档中，第一张工作表的缺省索引是0
        Sheet sheet = workbook.getSheetAt(0);
        if(null == sheet){
            throw new Exception("当前工作表没有内容");
        }
        //获取Excel文件中的开始行与结束行
        int rows = sheet.getPhysicalNumberOfRows();
        //遍历行
        for(int i=1; i<rows; i++){
            //获取当前行
            Row row = sheet.getRow(i);
            //行不为空
            if(null != row){
                //获取当前行的所有列数
                int cells = row.getPhysicalNumberOfCells();
                //存放当前行的所有数据
                StringBuffer value = new StringBuffer();
                //遍历列
                for(int j=0; j<cells; j++){
                    //获取当前列的值
                    Cell cell = row.getCell(j);
                    if(null != cell){
                        //将读取的值都转为string类型，避免数字加小数点无法入库的情况
                        cell.setCellType(CellType.STRING);
                        switch (cell.getCellTypeEnum()){
                            case STRING:
                                value.append(cell.getStringCellValue()).append(",");
                                break;
                            default:
                                value.append("null");
                                break;
                        }
                    }
                }
                String[] values = value.toString().split(",");
                User user = new User();
                user.setId(Long.parseLong(values[0]));
                user.setName(values[1]);
                user.setAge(Integer.parseInt(values[2]));
                user.setAddress(values[3]);
                userDao.save(user);
            }
        }
    }
}
