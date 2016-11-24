/**
 * This class is generated by jOOQ
 */
package lukaszlusz.sql.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import lukaszlusz.sql.generated.Keys;
import lukaszlusz.sql.generated.Warehousedb;
import lukaszlusz.sql.generated.tables.records.StatusesRecord;

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
public class Statuses extends TableImpl<StatusesRecord> {

    private static final long serialVersionUID = 527815174;

    /**
     * The reference instance of <code>warehousedb.statuses</code>
     */
    public static final Statuses STATUSES = new Statuses();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StatusesRecord> getRecordType() {
        return StatusesRecord.class;
    }

    /**
     * The column <code>warehousedb.statuses.Status</code>.
     */
    public final TableField<StatusesRecord, String> STATUS = createField("Status", org.jooq.impl.SQLDataType.VARCHAR.length(30).nullable(false), this, "");

    /**
     * Create a <code>warehousedb.statuses</code> table reference
     */
    public Statuses() {
        this("statuses", null);
    }

    /**
     * Create an aliased <code>warehousedb.statuses</code> table reference
     */
    public Statuses(String alias) {
        this(alias, STATUSES);
    }

    private Statuses(String alias, Table<StatusesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Statuses(String alias, Table<StatusesRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<StatusesRecord> getPrimaryKey() {
        return Keys.KEY_STATUSES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<StatusesRecord>> getKeys() {
        return Arrays.<UniqueKey<StatusesRecord>>asList(Keys.KEY_STATUSES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statuses as(String alias) {
        return new Statuses(alias, this);
    }

    /**
     * Rename this table
     */
    public Statuses rename(String name) {
        return new Statuses(name, null);
    }
}
