/**
 * This class is generated by jOOQ
 */
package lukaszlusz.sql.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import lukaszlusz.sql.generated.Keys;
import lukaszlusz.sql.generated.Warehousedb;
import lukaszlusz.sql.generated.tables.records.CategoriesRecord;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Categories extends TableImpl<CategoriesRecord> {

    private static final long serialVersionUID = 597638186;

    /**
     * The reference instance of <code>warehousedb.categories</code>
     */
    public static final Categories CATEGORIES = new Categories();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CategoriesRecord> getRecordType() {
        return CategoriesRecord.class;
    }

    /**
     * The column <code>warehousedb.categories.Category</code>.
     */
    public final TableField<CategoriesRecord, String> CATEGORY = createField("Category", org.jooq.impl.SQLDataType.VARCHAR.length(30).nullable(false), this, "");

    /**
     * Create a <code>warehousedb.categories</code> table reference
     */
    public Categories() {
        this("categories", null);
    }

    /**
     * Create an aliased <code>warehousedb.categories</code> table reference
     */
    public Categories(String alias) {
        this(alias, CATEGORIES);
    }

    private Categories(String alias, Table<CategoriesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Categories(String alias, Table<CategoriesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Warehousedb.WAREHOUSEDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CategoriesRecord> getPrimaryKey() {
        return Keys.KEY_CATEGORIES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CategoriesRecord>> getKeys() {
        return Arrays.<UniqueKey<CategoriesRecord>>asList(Keys.KEY_CATEGORIES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categories as(String alias) {
        return new Categories(alias, this);
    }

    /**
     * Rename this table
     */
    public Categories rename(String name) {
        return new Categories(name, null);
    }
}
