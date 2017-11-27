package win.sinno.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * win.sinno.common.util.MongoDbTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2017/9/29
 */
public class MongoDbTest {

    @Test
    public void test() {
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("COMM_DB");
        DBCollection coll = db.getCollection("seller");
        DBObject dbObject = new BasicDBObject();
//        dbObject.put("notes.gmtCreated", new BasicDBObject().put("$gte", "1505318400000"));
//        dbObject.put("notes.content", new BasicDBObject().put("$regex", "/分配客户给*/"));
        DBCursor dbCursor = coll.find(dbObject);

        Map<String, Note> map = new HashMap<>();

        DBObject dbObject1 = null;
        try {
            while ((dbObject1 = dbCursor.next()) != null) {
                Object con = dbObject1.get("notes");
                if (con != null) {
                    String s = String.valueOf(con);
//                System.out.println(s);

                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        List<Note> notes = mapper.readValue(s, new TypeReference<List<Note>>() {
                        });

                        for (Note n : notes) {
                            if (n.createTime > 1505318400000l
                                    && n.content.startsWith("分配客户")
                                    ) {
                                if (map.containsKey(n.nick)) {
                                    if (map.get(n.nick).createTime < n.createTime) {
                                        map.put(n.nick, n);
                                    }
                                } else {
                                    map.put(n.nick, n);
                                }
//                            System.out.println(n);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<Map.Entry<String, Note>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Note>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Note> entry = it.next();
            System.out.println(entry.getValue());
        }


        System.out.println(dbCursor.count());

    }

    public static class Note {

        private String content;
        private Long createTime;
        private String nick;
        private Long refUid;
        private String operator;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public Long getRefUid() {
            return refUid;
        }

        public void setRefUid(Long refUid) {
            this.refUid = refUid;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return operator + "," + content + "," + DateUtil.format(createTime, "YYYY-MM-dd HH:mm") + "," + nick;
        }
    }


}
