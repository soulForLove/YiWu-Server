package test.com.yiwu.changething.sec1.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * QiniuService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 7, 2017</pre>
 */
public class QiniuServiceTest {

    /**
     * Method: rename()
     */
    @Test
    public void testRename() throws Exception {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create("jEzZX-s-3IvmqM1hnt7r9G-Lp_BMrPNZyg8G_epx", "U7SEYevkRL9duE7s5umDB9NCZi7_HkEfJK2nbUKM");
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.move("yiwu-image",
                    "954610ff-6efc-4f2d-b899-aefc5e95ae3f/fkkQZSIbLVBPiBJ73T992jifNUPXqdKs",
                    "yiwu-image", "changeName");
        } catch (QiniuException ex) {
            //如果遇到异常，说明移动失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * Method: delete()
     */
    @Test
    public void testDelete() throws Exception {
        Configuration cfg = new Configuration(Zone.zone0());

        Auth auth = Auth.create("jEzZX-s-3IvmqM1hnt7r9G-Lp_BMrPNZyg8G_epx", "U7SEYevkRL9duE7s5umDB9NCZi7_HkEfJK2nbUKM");
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete("yiwu-image", "954610ff-6efc-4f2d-b899-aefc5e95ae3f/mTDQ4jPqbHeiIdid6P7K7iAbtGbRrsJN");
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }


} 
