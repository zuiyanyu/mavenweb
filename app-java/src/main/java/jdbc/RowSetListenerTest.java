package jdbc;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class RowSetListenerTest implements RowSetListener {
    @Override
    public void rowSetChanged(RowSetEvent event) {
        System.out.println("rowSetChanged");
    }

    @Override
    public void rowChanged(RowSetEvent event) {
        System.out.println("rowChanged");

    }

    @Override
    public void cursorMoved(RowSetEvent event) {
        System.out.println("cursorMoved");

    }
}
