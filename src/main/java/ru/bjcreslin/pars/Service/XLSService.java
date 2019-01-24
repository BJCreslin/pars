package ru.bjcreslin.pars.Service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import ru.bjcreslin.pars.model.ProductOur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class XLSService {

    /**
     * Данные из xls- файла.
     * Возвращает List ProductOur с заполененным кодом, остатками на Центральном и СНТБ8
     *
     * @return List<ProductOur>
     */
    public static List<ProductOur> getBaza8List(InputStream inputStream) {
        List<ProductOur> itemtableList = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);

            boolean flagToStop = false;
            int iPos = 0;
            int shiftV = 7;
            int stolbecCode = centralStolbecCodeFind();
            int stolbecNumber = centralStolbecNumberFind();
            int stolbecBaza8 = stolbecBaza8Find();
            int stolbecName = 5;


            while (!flagToStop) {
                int code = 0;
                boolean propuskaem = false;
                try {
                    code = (int) sheet.getRow(iPos + shiftV).getCell(stolbecCode).getNumericCellValue();
                } catch (IllegalStateException | NullPointerException Illex) {
                    propuskaem = true;
                    flagToStop = true;
                }

                int remnantsCentral = 0;
                int remnantsBaza8 = 0;
                try {
                    remnantsCentral = (int) sheet.getRow(iPos + shiftV).getCell(stolbecNumber).getNumericCellValue();
                } catch (NullPointerException npe) {
                    propuskaem = true;
                    flagToStop = true;
                }
                try {
                    remnantsBaza8 = (int) sheet.getRow(iPos + shiftV).getCell(stolbecBaza8).getNumericCellValue();
                } catch (NullPointerException npe) {
                    propuskaem = true;
                    flagToStop = true;
                }


                if ((!flagToStop) && (!propuskaem)) {
                    ProductOur itemTable = new ProductOur();
                    try {

                        itemTable.setName(sheet.getRow(iPos + shiftV).getCell(stolbecName).getStringCellValue());

                    } catch (IllegalStateException Excx) {
                        itemTable.setName(String.valueOf(sheet.getRow(iPos + shiftV).getCell(stolbecName).getNumericCellValue()));
                    }
                    itemTable.setCode(code);
                    itemTable.setCentral(remnantsCentral);
                    itemTable.setBase(remnantsBaza8);

                    itemtableList.add(itemTable);
                    iPos++;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemtableList;
    }


    /**
     * Данные из xls- файла.
     * Возвращает List ProductOur с заполененным кодом, нужными остатками на СНТБ8
     *названием и групп
     * @return List<ProductOur>
     */
    public static List<ProductOur> getItemList(InputStream inputStream) {
        List<ProductOur> itemtableList = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);


            int iPos = 0;
            int shiftV = 7;
            int stolbecCode = centralStolbecCodeFind();
            int stolbecNumber = 6;
            int stolbecName = centralStolbecNameFind();
            int stolbecGroup = centralStolbecGroupFind();


            boolean flagToStop = false;
            while (!flagToStop) {
                int code;
                boolean propuskaem = false;

                HSSFCell codeCell;
                try {
                    //System.out.println(sheet.getSheetName());
                    codeCell = sheet.getRow(iPos + shiftV).getCell(stolbecCode);
                } catch (NullPointerException npe) {
                    if (iPos > 10000) {
                        System.out.println("Ipos");
                        break;
                    }
                    iPos++;
                    continue;
                }


                DataFormatter fmtCode = new DataFormatter();
                String dataCodeFromCell = fmtCode.formatCellValue(codeCell);
                if (dataCodeFromCell.isEmpty()) {
                    iPos++;
                    continue;
                }
                if (dataCodeFromCell.equals("THEEND")) {
                    flagToStop = true;
                    propuskaem = true;
                    break;
                }
                dataCodeFromCell = dataCodeFromCell.replaceAll("(,00)|\\D", "");
                code = Integer.parseInt(dataCodeFromCell);
//                if (code == 163964) {
//                    System.out.println("163964");
//                }


                int number = 0;
                HSSFCell yourCell = sheet.getRow(iPos + shiftV).getCell(stolbecNumber);
                DataFormatter fmt = new DataFormatter();
                String dataFromCell = fmt.formatCellValue(yourCell);

//                if (code == 163964) {
//                    System.out.println(dataFromCell);
//                }
                String dataFromCel2 = dataFromCell.replaceAll("(,00)|\\D", "");
                try {
                    if (!dataFromCell.isEmpty()) {
                        number = Integer.parseInt(dataFromCel2);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("code " + code + " " + dataFromCell + "  x" + dataFromCel2);
                    exit(15);
                }


                if ((number > 0) && (!flagToStop) && (!propuskaem)) {
                    ProductOur item;
                    try {
                        item = new ProductOur(code, number, sheet.getRow(iPos + shiftV).getCell(stolbecName).getStringCellValue());
                    } catch (IllegalStateException Excx) {
                        item = new ProductOur(code, number, String.valueOf(sheet.getRow(iPos + shiftV).getCell(stolbecName).getNumericCellValue()));
                    }

                    /*Записываем группу в item*/
                    String groupe;
                    try {
                        groupe = sheet.getRow(iPos + shiftV).getCell(stolbecGroup).getStringCellValue();
                    } catch (Exception ex) {
                        groupe = "";
                    }
                    item.setGroupe(groupe);

                    itemtableList.add(item);
                }
                iPos++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemtableList;
    }


    private static int centralStolbecNameFind() {
        return 2;
    }

    private static int centralStolbecGroupFind() {
        return 0;
    }

    private static int centralStolbecNumberFind() {
        return 7;
    }


    private static int stolbecBaza8Find() {
        return 6;
    }

    private static int centralStolbecCodeFind() {
        return 1;
    }
}

