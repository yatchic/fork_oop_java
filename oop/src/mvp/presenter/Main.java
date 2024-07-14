package mvp.presenter;

import mvp.model.file_maniger.TreeFileManager;
import mvp.model.objects_of_tables.Tables;
import mvp.model.query.Query;
import mvp.model.tree.Tree;
import mvp.presenter.parse.Parser;
import mvp.presenter.read.Read;
import mvp.view.errors.Errors;
import mvp.view.info.Info;
import mvp.view.messages.Messages;

public class Main {




  public static void main(String[]args){
      Read read=new Read();
      Tables tables = new Tables();
      Errors errors=new Errors();
      Query query=new Query();
      Messages messages=new Messages();
      Info info=new Info();
      TreeFileManager treeFileManager=new TreeFileManager();
        Parser p= new Parser(tables,read,errors,query,messages,info,treeFileManager);
        p.parse();//---Читает из консоли команды.

  }


}
