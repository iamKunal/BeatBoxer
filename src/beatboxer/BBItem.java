/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

/**
 *
 * @author kunal
 */
public class BBItem {

    protected int Id;
    protected String name;

    public BBItem(int Id, String name) {
        try {
            this.Id = Id;
        } catch (NullPointerException e) {
            this.Id = -1;
        }
        this.setName(name);
    }

    public BBItem() {
        this.Id = 0;
        this.name = "Unknown";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            this.name = name;
        } catch (NullPointerException e) {
            this.name = "";
        }
    }

    public int getId() {
        return this.Id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int toInt() {
        return this.Id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BBItem other = (BBItem) obj;
        if (this.Id != other.Id) {
            return false;
        }
        return true;
    }
}
