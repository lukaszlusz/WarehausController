/**
 * This class is generated by jOOQ
 */
package lukaszlusz.sql.generated.tables.records;


import javax.annotation.Generated;

import lukaszlusz.sql.generated.tables.Statuses;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


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
public class StatusesRecord extends UpdatableRecordImpl<StatusesRecord> implements Record1<String> {

    private static final long serialVersionUID = -489768679;

    /**
     * Setter for <code>warehousedb.statuses.Status</code>.
     */
    public void setStatus(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>warehousedb.statuses.Status</code>.
     */
    public String getStatus() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row1<String> valuesRow() {
        return (Row1) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Statuses.STATUSES.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatusesRecord value1(String value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatusesRecord values(String value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StatusesRecord
     */
    public StatusesRecord() {
        super(Statuses.STATUSES);
    }

    /**
     * Create a detached, initialised StatusesRecord
     */
    public StatusesRecord(String status) {
        super(Statuses.STATUSES);

        set(0, status);
    }
}