package mvp.presenter.parse;

import mvp.model.any.AnyClass;
import mvp.model.file_maniger.TreeFileManager;
import mvp.model.objects_of_tables.Tables;
import mvp.model.order.TreeSorter;
import mvp.model.query.Query;
import mvp.model.tree.Tree;
import mvp.presenter.read.Read;
import mvp.view.errors.Errors;
import mvp.view.info.Info;
import mvp.view.messages.Messages;


import java.util.Arrays;
import java.util.List;


public class Parser implements InterfaceForParser {
    private Tables tables;
    private Read read;
    private Errors errors;
    private Query query;
    private Messages messages;
    private Info info;
    private TreeFileManager treeFileManager;


    public Parser(Tables tables, Read read, Errors errors,Query query,Messages messages,Info info,TreeFileManager treeFileManager) {
        this.tables = tables;
        this.read = read;
        this.errors = errors;
        this.query = query;
        this.messages = messages;
        this.info = info;
        this.treeFileManager = treeFileManager;
    }

    public Parser() {

    }



@Override
    public void parse() {
        AnyClass a = new AnyClass();
        List<String> types = Arrays.asList("Integer", "String", "Double", "Boolean");
        try {
            while (true) {
                String line = read.read();
                if (a.findTextB(line, "[А-Яа-яA-Za-z]+\\s+(Integer|String|Double|Boolean)\\s+(Integer|String|Double|Boolean)\\s+(Integer|String|Double|Boolean)\\s+(Integer|String|Double|Boolean)\\s+(Integer|String|Double|Boolean)\\s+(Integer|String|Double|Boolean)")) {
                    List<String> constructor = a.tokens(line);
                    String tableName = constructor.get(0);
                    String firstType = constructor.get(1);
                    String secondType = constructor.get(2);
                    String thirdType = constructor.get(3);
                    String fourthType = constructor.get(4);
                    String fifthType = constructor.get(5);
                    String sixthType = constructor.get(6);


                    for (String t1 : types) {
                        for (String t2 : types) {
                            for (String t3 : types) {
                                for (String t4 : types) {
                                    for (String t5 : types) {
                                        for (String t6 : types) {
                                            if (firstType.equals(t1) && secondType.equals(t2) && thirdType.equals(t3) && fourthType.equals(t4) && fifthType.equals(t5) && sixthType.equals(t6)) {
                                                Tree<?, ?, ?, ?, ?, ?> tree = createTree(t1, t2, t3, t4, t5, t6);
                                                tables.add(tree, tableName);

                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                if (a.findTextB(line, "добавить\\s+[А-Яа-яA-Za-z]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s*")) {
                    List<String> added = a.tokens(line);

                    String tableName = added.get(1);
                    String firstType = added.get(2);
                    Object firstTypeO=null;

                    if(a.findTextB(firstType,"^\\d+$")) {firstTypeO=a.stringToInteger(firstType);}
                    if(a.findTextB(firstType,"^\\d+\\.\\d+$")) {firstTypeO=a.stringToDouble(firstType);}
                      if(a.findTextB(firstType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {firstTypeO=firstType;}
                    if(a.findTextB(firstType,"^false|False|FALSE|true|True|TRUE$")) {firstTypeO=a.stringToBoolean(firstType);}

                    String secondType = added.get(3);

                    Object secondTypeO=null;

                    if(a.findTextB(secondType,"^\\d+$")) {secondTypeO=a.stringToInteger(secondType);}
                    if(a.findTextB(secondType,"^\\d+\\.\\d+$")) {secondTypeO=a.stringToDouble(secondType);}
                    if(a.findTextB(secondType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {secondTypeO=secondType;}
                    if(a.findTextB(secondType,"^false|False|FALSE|true|True|TRUE$")) {secondTypeO=a.stringToBoolean(secondType);}
                    String thirdType = added.get(4);
                    Object thirdTypeO=null;

                    if(a.findTextB(thirdType,"^\\d+$")) {thirdTypeO=a.stringToInteger(thirdType);}
                    if(a.findTextB(thirdType,"^\\d+\\.\\d+$")) {thirdTypeO=a.stringToDouble(thirdType);}
                    if(a.findTextB(thirdType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {thirdTypeO=thirdType;}
                    if(a.findTextB(thirdType,"^false|False|FALSE|true|True|TRUE$")) {thirdTypeO=a.stringToBoolean(thirdType);}
                    String fourthType = added.get(5);
                    Object fourthTypeO=null;

                    if(a.findTextB(fourthType,"^\\d+$")) {fourthTypeO=a.stringToInteger(fourthType);}
                    if(a.findTextB(fourthType,"^\\d+\\.\\d+$")) {fourthTypeO=a.stringToDouble(fourthType);}
                    if(a.findTextB(fourthType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {fourthTypeO=fourthType;}
                    if(a.findTextB(fourthType,"^false|False|FALSE|true|True|TRUE$")) {fourthTypeO=a.stringToBoolean(fourthType);}


                    String fifthType = added.get(6);

                    Object fifthTypeO=null;

                    if(a.findTextB(fifthType,"^\\d+$")) {fifthTypeO=a.stringToInteger(fifthType);}
                    if(a.findTextB(fifthType,"^\\d+\\.\\d+$")) {fifthTypeO=a.stringToDouble(fifthType);}
                    if(a.findTextB(fifthType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {fifthTypeO=fifthType;}
                    if(a.findTextB(fifthType,"^false|False|FALSE|true|True|TRUE$")) {fifthTypeO=a.stringToBoolean(fifthType);}


                    String sixthType = added.get(7);

                    Object sixthTypeO=null;

                    if(a.findTextB(sixthType,"^\\d+$")) {sixthTypeO=a.stringToInteger(sixthType);}
                    if(a.findTextB(sixthType,"^\\d+\\.\\d+$")) {sixthTypeO=a.stringToDouble(sixthType);}
                    if(a.findTextB(sixthType,"^[А-Яа-яA-Za-z\\d\\.]+$")) {sixthTypeO=sixthType;}
                    if(a.findTextB(sixthType,"^false|False|FALSE|true|True|TRUE$")) {sixthTypeO=a.stringToBoolean(sixthType);}



                    Tree tree=tables.getTreeByName(tableName);
                    String currrentTableName=tables.getNameByTree(tree);
                        if(tree==null){errors.showError("такой таблицы нет");}else {
                            tree.add(firstTypeO,secondTypeO,thirdTypeO,fourthTypeO,fifthTypeO,sixthTypeO);
                            messages.showMessage("значения: "+firstTypeO+" ,"+secondTypeO+" ,"+thirdTypeO+" ,"+fourthTypeO+" ,"+fifthTypeO+" ,"+sixthTypeO+" "+"добавлены в таблицу \""+currrentTableName+"\"");
                        }


                }
                if (a.findTextB(line, "удалить\\s+[А-Яа-яA-Za-z]+")) {
                    List<String> delete = a.tokens(line);
                    String del = delete.get(0);
                     String tableName = delete.get(1);
                    Tree tree1=tables.getTreeByName(tableName);
                    if(tree1==null){errors.showError("такой таблицы нет");}
                    else{
                        tables.remove(tableName);
                        Tree tree = tables.getTreeByName(tableName);
                        if (tree == null) {
                            messages.showMessage("таблица удалена");
                        }
                    }



                }

                if (a.findTextB(line, "сохранить\\s+[А-Яа-яA-Za-z]+\\s+.+\\s*")) {
                    List<String> save = a.tokens(line);
                    String sv = save.get(0);
                    String tableName = save.get(1);
                    String path = save.get(2);
                    a.deb(path);
                    path=a.replaceTextAll(path,"\\|/","\\\\");
                    Tree tree=tables.getTreeByName(tableName);
                    if(tree==null){errors.showError("такой таблицы нет");}
                    else{
                        TreeSorter treeSorter= tree.getTreeSorter();
                        treeFileManager.setTreeSorter(treeSorter);
                        treeFileManager.writeToFile(path);
                            messages.showMessage("таблица \""+tableName+"\" сохранена в\""+path+"\"");
                        }

                }

                if (a.findTextB(line, "показать\\s+[А-Яа-яA-Za-z]+\\s+(по_возрастанию|по_убыванию)\\s*")) {
                    List<String> show = a.tokens(line);
                    String sh = show.get(0);
                   String tableName = show.get(1);
                    String order = show.get(2);
                    order=a.replaceTextAll(order,"по_возрастанию","по возрастанию");
                    order=a.replaceTextAll(order,"по_убыванию","по убыванию");
                     if (order.equals("по возрастанию")||order.equals("по убыванию")){}else{errors.showError("значения:\"по возрастанию\"и\"по убыванию\" не совпадают");}
                   Tree tree=tables.getTreeByName(tableName);
                 if(tree==null){errors.showError("такой таблицы нет");}
                 else{
                       TreeSorter treeSorter= tree.getTreeSorter();
                      query.setTreeSorter(treeSorter);
                      query.getByAll(order);
                         messages.showMessage("таблица выведена ");
                    }

                }


                if (a.findTextB(line, "показать\\s+[А-Яа-яA-Za-z]+\\s+(1колонку|2колонку|3колонку|4колонку|5колонку|6колонку)\\s*")) {
                    List<String> showColumns = a.tokens(line);

                      String sh = showColumns.get(0);
                    String tableName = showColumns.get(1);
                    String column = showColumns.get(2);



                   Tree tree=tables.getTreeByName(tableName);
                   if(tree==null){errors.showError("такой таблицы нет");}
                    else{
                        TreeSorter treeSorter= tree.getTreeSorter();
                        query.setTreeSorter(treeSorter);
                        if (a.findTextB(column,"1колонку")){ query.getByA("по возрастанию");messages.showMessage("выведена колонка 1");}
                       if (a.findTextB(column,"2колонку")){ query.getByB("по возрастанию");messages.showMessage("выведена колонка 2");}
                       if (a.findTextB(column,"3колонку")){ query.getByC("по возрастанию");messages.showMessage("выведена колонка 3");}
                       if (a.findTextB(column,"4колонку")){ query.getByD("по возрастанию");messages.showMessage("выведена колонка 4");}
                       if (a.findTextB(column,"5колонку")){ query.getByE("по возрастанию");messages.showMessage("выведена колонка 5");}
                       if (a.findTextB(column,"6колонку")){ query.getByF("по возрастанию");messages.showMessage("выведена колонка 6");}

                    }

                }


                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Tree<?, ?, ?, ?, ?, ?> createTree(String t1, String t2, String t3, String t4, String t5, String t6) {
        return new Tree<>(getClassType(t1), getClassType(t2), getClassType(t3), getClassType(t4), getClassType(t5), getClassType(t6));
    }


    private Class<?> getClassType(String type) {
        switch (type) {
            case "Integer":
                return Integer.class;
            case "String":
                return String.class;
            case "Double":
                return Double.class;
            case "Boolean":
                return Boolean.class;
            default:
                throw new IllegalArgumentException("неизвестный тип: " + type);
        }
    }
}
