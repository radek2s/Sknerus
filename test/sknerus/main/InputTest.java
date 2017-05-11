package sknerus.main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author radek2s
 * @version created on &{DATE}.
 *          Description:
 */
public class InputTest {

    @Before
    public void prepareApp(){
        AppCore.getInstance().data.clear();
    }
    @Test
    public void validDataReadTest(){

        //Input values
        String separator = ",";
        String row = "1451606432,1,income,Payment,PNWA,27.16,171.33,11%,Client30;";

        //Add Data
        String[] csvData = row.split(separator);
        AppCore.getInstance().addData(csvData);

        String date = "1451606432";
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Integer.valueOf(date) * 1000L));

        //Validate each data
        assertEquals(dateAsText,AppCore.getInstance().data.get(0).getCreationDate());
        assertEquals("1",AppCore.getInstance().data.get(0).getNumber());
        assertEquals("income",AppCore.getInstance().data.get(0).getDocType());
        assertEquals("Platnosc",AppCore.getInstance().data.get(0).getType());
        assertEquals("PNWA",AppCore.getInstance().data.get(0).getName());
        assertEquals(27.16f,AppCore.getInstance().data.get(0).getValue(),0.001);
        assertEquals(171.33f,AppCore.getInstance().data.get(0).getAmount(),0.001);
        assertEquals("11%",AppCore.getInstance().data.get(0).getTax());
        assertEquals("Client30;",AppCore.getInstance().data.get(0).getClient());

    }
    @After
    public void resetData(){
        AppCore.getInstance().data.clear();
    }

    /**
     * Test with wrong decimal separator
     */
    @Test
    public void inValidDataReadTest() {


        //Input values
        String separator = ",";
        String row = "1451606432,1,income,Payment,PNWA,27,16,171,33,11%,Client30;";

        //Add Data
        String[] csvData = row.split(separator);
        AppCore.getInstance().addData(csvData);

        String date = "1451606432";
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Integer.valueOf(date) * 1000L));

        //Validate each data
        assertEquals(dateAsText,AppCore.getInstance().data.get(0).getCreationDate());
        assertEquals("1",AppCore.getInstance().data.get(0).getNumber());
        assertEquals("income",AppCore.getInstance().data.get(0).getDocType());
        assertEquals("Platnosc",AppCore.getInstance().data.get(0).getType());
        assertEquals("PNWA",AppCore.getInstance().data.get(0).getName());
        assertEquals(27.16f,AppCore.getInstance().data.get(0).getValue(),0.001);
        assertEquals(171.33f,AppCore.getInstance().data.get(0).getAmount(),0.001);
        assertEquals("11%",AppCore.getInstance().data.get(0).getTax());
        assertEquals("Client30;",AppCore.getInstance().data.get(0).getClient());


    }


}
