package com.rmuti.ecp.nipat.tablejason;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL("http://soeleh.com:8888");
            Scanner sc = new Scanner(url.openStream());
            StringBuffer buf = new StringBuffer();
            while(sc.hasNext()){
                buf.append(sc.next());
            }
            //Log.v("test", buf.toString());
            JSONObject jsonObject = new JSONObject(buf.toString());
            JSONArray dataArr = jsonObject.getJSONArray("data");
            TableLayout tlb = (TableLayout)findViewById(R.id.tlb);

            for(int i=0; i<dataArr.length(); i++){
                TableRow tRow = new TableRow(this);
                TextView txt = new TextView(this);
                //  int d = (int)dataArr.get(i);
                // String d = (String) dataArr.get(i).toString();
                // txt.setText(d+"");
                txt.setText(dataArr.get(i).toString());
                tRow.addView(txt);
                tlb.addView(tRow);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
