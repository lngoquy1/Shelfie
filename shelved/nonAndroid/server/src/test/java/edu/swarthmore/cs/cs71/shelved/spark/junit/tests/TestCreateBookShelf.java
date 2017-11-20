package edu.swarthmore.cs.cs71.shelved.spark.junit.tests;
import edu.swarthmore.cs.cs71.shelved.model.server.HibBookShelf;
import edu.swarthmore.cs.cs71.shelved.model.server.HibRowShelf;
import edu.swarthmore.cs.cs71.shelved.model.server.HibUser;
import org.junit.Assert;
import org.junit.Test;

public class TestCreateBookShelf {
    @Test
    public void TestNewShelf(){

        int index = 1;
        HibRowShelf hibRowShelf = new HibRowShelf();
        HibBookShelf newBookShelf = new HibBookShelf();
        newBookShelf.addRowShelf(index, hibRowShelf);

        Assert.assertEquals(hibRowShelf, newBookShelf.getRowShelf(index));
    }
}
