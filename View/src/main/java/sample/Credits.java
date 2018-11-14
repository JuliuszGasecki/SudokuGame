package sample;

import java.util.ListResourceBundle;

public class Credits extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "Author1"   , "Juliusz Gasecki"  },
            { "Author2"   , "Mateusz Lapies"  },
    };

}
