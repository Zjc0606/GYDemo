package com.zjc.greendao.dao;

import android.database.Cursor;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.DatabaseStatement;

import com.zjc.greendao.entity.Tasktab;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TASKTAB".
*/
public class TasktabDao extends AbstractDao<Tasktab, Void> {

    public static final String TABLENAME = "TASKTAB";

    /**
     * Properties of entity Tasktab.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Cplannum = new Property(0, String.class, "cplannum", false, "CPLANNUM");
        public final static Property AssetNum = new Property(1, String.class, "assetNum", false, "ASSET_NUM");
        public final static Property Clinenum = new Property(2, String.class, "clinenum", false, "CLINENUM");
        public final static Property Partloc = new Property(3, String.class, "partloc", false, "PARTLOC");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property Standard = new Property(5, String.class, "standard", false, "STANDARD");
        public final static Property Contentflag = new Property(6, String.class, "contentflag", false, "CONTENTFLAG");
        public final static Property Result = new Property(7, String.class, "result", false, "RESULT");
        public final static Property Record = new Property(8, String.class, "record", false, "RECORD");
    };


    public TasktabDao(DaoConfig config) {
        super(config);
    }
    
    public TasktabDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TASKTAB\" (" + //
                "\"CPLANNUM\" TEXT," + // 0: cplannum
                "\"ASSET_NUM\" TEXT," + // 1: assetNum
                "\"CLINENUM\" TEXT," + // 2: clinenum
                "\"PARTLOC\" TEXT," + // 3: partloc
                "\"CONTENT\" TEXT," + // 4: content
                "\"STANDARD\" TEXT," + // 5: standard
                "\"CONTENTFLAG\" TEXT," + // 6: contentflag
                "\"RESULT\" TEXT," + // 7: result
                "\"RECORD\" TEXT);"); // 8: record
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASKTAB\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(DatabaseStatement stmt, Tasktab entity) {
        stmt.clearBindings();
 
        String cplannum = entity.getCplannum();
        if (cplannum != null) {
            stmt.bindString(1, cplannum);
        }
 
        String assetNum = entity.getAssetNum();
        if (assetNum != null) {
            stmt.bindString(2, assetNum);
        }
 
        String clinenum = entity.getClinenum();
        if (clinenum != null) {
            stmt.bindString(3, clinenum);
        }
 
        String partloc = entity.getPartloc();
        if (partloc != null) {
            stmt.bindString(4, partloc);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String standard = entity.getStandard();
        if (standard != null) {
            stmt.bindString(6, standard);
        }
 
        String contentflag = entity.getContentflag();
        if (contentflag != null) {
            stmt.bindString(7, contentflag);
        }
 
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(8, result);
        }
 
        String record = entity.getRecord();
        if (record != null) {
            stmt.bindString(9, record);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Tasktab readEntity(Cursor cursor, int offset) {
        Tasktab entity = new Tasktab( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // cplannum
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // assetNum
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // clinenum
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // partloc
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // standard
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // contentflag
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // result
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // record
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Tasktab entity, int offset) {
        entity.setCplannum(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setAssetNum(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setClinenum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPartloc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setStandard(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContentflag(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setResult(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRecord(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Tasktab entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Tasktab entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
