package edu.swarthmore.cs.cs71.shelved;

public interface Author {

    String getFullName();
    String getLastName();

    public String getAuthorName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (fullName != null ? !fullName.equals(author.fullName) : author.fullName != null) return false;
        return lastName != null ? lastName.equals(author.lastName) : author.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
