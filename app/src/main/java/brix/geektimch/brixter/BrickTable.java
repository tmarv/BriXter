package brix.geektimch.brixter;

/**
 * Created by gkt on 11.04.16.
 */
/**
 * Created by gkt on 30.03.16.
 */
public class BrickTable {

    private BrickLine [] table=new BrickLine[16];

    BrickTable(int sWidth, int sHeight, int visibleLines){

        BrickLine localLine=null;

        for(int i=0;i<16;i++){
            if(i<visibleLines){
                localLine= new BrickLine(sWidth,sHeight,true,i);
            }
            else{
                localLine= new BrickLine(sWidth,sHeight,false,i);
            }
            table[i]=localLine;
        }

    }//end of constructor

    public int howBig(){
        return table.length;
    }

    public BrickLine[] getTable(){
        return table;
    }

    public BrickLine getLine(int index){
        return table[index];
    }
    public void setLine(BrickLine bl, int index){
        table[index]=bl;
    }


}
