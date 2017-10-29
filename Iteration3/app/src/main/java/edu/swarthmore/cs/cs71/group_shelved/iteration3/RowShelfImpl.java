package edu.swarthmore.cs.cs71.group_shelved.iteration3;

public class RowShelfImpl implements RowShelf {
    @Override
    public void addBook(ShelvedBook shelvedBook, int position) {
        rowList.add(position, shelvedBook);
    }

    @Override
    public void removeBook(ShelvedBook shelvedBook) {
        rowList.remove(shelvedBook);
    }

    @Override
    public void setPosition(ShelvedBook shelvedBook, int position) {
        rowList.remove(shelvedBook);
        rowList.add(position, shelvedBook);
    }

}
