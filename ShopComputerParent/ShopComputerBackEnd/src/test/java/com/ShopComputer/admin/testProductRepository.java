package com.ShopComputer.admin;

import com.ShopComputer.EntityCommon.Brand;
import com.ShopComputer.EntityCommon.Category;
import com.ShopComputer.EntityCommon.Product;
import com.ShopComputer.admin.brand.BrandRepository;
import com.ShopComputer.admin.category.CategoryRepository;
import com.ShopComputer.admin.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class testProductRepository {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void saveProduct(){
//        List<Category> listCategory= new ArrayList<>();
//        Category c1 = categoryRepository.findById(1).get();
//        Category c2 = categoryRepository.findById(2).get();
//        Category c3 = categoryRepository.findById(11).get();
//
//        listCategory.addAll(List.of(c1,c2,c3));
//
//        Brand brand1= brandRepository.findById(4).get();
//
//        Product product = new Product("[New 100%] Lenovo Legion 5 R7000 ARP8 (Ryzen 7-7735H, 16GB, 512GB, RTX 4060 8GB, 15.6'' 2K+ 165Hz)", "Legion R7000 2023",  "Thiết kế\n" +
//                "\n" +
//                "Thiết kế của Legion R7000 2023 thay vì có hơi hướng mỏng nhẹ như người anh em Slim thì nó lại là 1 phiên bản đơn giản hóa hơn của chiếc Legion 5 với thiết kế có phần vuông vức và tối giản hơn rất nhiều.","Sở hữu chiếc màn hình 15.6 inch, Legion R7000 2023 cho phép người dùng có thể đắm mình vào những thước phim hấp dẫn hay các tựa game đầy kịch tính với không gian hiển thị rộng lớn có kích thước màn hình rộng lớn cùng độ phân giải 2K+ (2560 x 1440), mang đến hình ảnh rõ nét, chân thật đến từng chi tiết. \n" +
//                "\n" +
//                "Hơn thế nữa, tần số quét 165 MHz còn mô phỏng lại từng chuyển động của nhân vật một cách mượt mà, hạn chế tối đa tình trạng giật xé hình khi bạn chơi các tựa game hành động nặng như bắn súng, đua xe, đấu vật,... mang đến những trải nghiệm trò chơi đầy thú vị nhưng đồng thời vẫn bảo vệ cho thị giác người xem trước những cường độ ánh sáng mạnh nhờ màn hình chống chói Anti Glare hiện đại.", 20000000,
//                0, 24000000,listCategory, brand1,  true,
//        true, 3400, 20, 25, new Date(),null,
//                null);
//        productRepository.save(product);


        List<Category> listCategory= new ArrayList<>();
        Category c1 = categoryRepository.findById(1).get();
        Category c2 = categoryRepository.findById(2).get();
        Category c3 = categoryRepository.findById(13).get();

        listCategory.addAll(List.of(c1,c2,c3));

        Brand brand1= brandRepository.findById(3).get();

        Product product = new Product("[New 100%] Dell Inspiron 16 5620 (Core i5-1240P, 16GB, 512GB, Iris Xe Graphics, 16\" FHD+ WVA)", "Dell Inspiron 16 5620",  "Dell Inspiron 16 5620 là chiếc laptop văn phòng nổi bật của hãng laptop Dell. Dell Inspiron 16 5620 sở hữu hiệu năng cực kì khỏe và đa nhiệm mượt, không chỉ dư sức xử lý hoàn hảo mọi tác vụ văn phòng mà còn giúp bạn chỉnh sửa ảnh, thiết kế đồ họa 2D vô cùng mượt mà và chơi được 1 số tựa game như LOL, FO4 khi sở hữu:"
              ,"Dell Inspiron 16 5620 sở hữu thiết kế tinh tế, sang trọng\n" +
                "\n" +
                "Dell Inspiron 16 5620 sở hữu kích thước 35,68 x 25,19 x 1,56 cm cùng trọng lượng chỉ 1,87 kg. Đây chính là kích thước cùng cân nặng tiêu chuẩn cho một chiếc laptop văn phòng ở thời điểm hiện tại, nhỏ gọn vừa vặn trong chiếc túi đựng để các bạn có thể mang theo mọi lúc mọi nơi, đảm bảo cho các bạn cảm giác thoải mái kể cả khi mang về nhà sau một ngày làm việc mệt mỏi.\n" +
                "\n" +
                "Chiếc Dell Inspiron 16 5620 sử dụng tone màu Bạc chủ đạo, kết hợp với phần viền màn hình đen, giúp cho tổng thể của chiếc laptop thêm phần sang trọng trong khi mặt B màu đen giúp tạo điểm nhấn cũng như tạo cảm giác tập trung hơn trong quá trình sử dụng. Nhờ phần vỏ được hoàn thiện từ chất liệu cứng cáp giúp cho chiếc máy mang lại cảm giác đầm tay ngay lần chạm đầu cũng như loại bỏ gần như hoàn toàn hiện tượng flex trên thân máy.", 14000000,
                0, 16000000,listCategory, brand1,  true,
                true, 1400, 20, 25, new Date(),null,
                null);
        productRepository.save(product);
    }


    @Test
    public void testAddImage(){
        Product product = productRepository.findById(1L).get();
        product.setMainImage("mainImage.png");
        product.addProductImageExtra("extraImg1");
        product.addProductImageExtra("extraImg2");
        product.addProductImageExtra("extraImg3");
        productRepository.save(product);
    }

    @Test
    public void testAddProductToWareHouse(){
        productRepository.addProductToWareHouse(16,25L);
    }

    @Test
    public void testRemoveProductFromWareHouse(){
        productRepository.removeProductFromWareHouse(2,25L);
    }

    @Test
    public void testSetProductFromWareHouse(){
        productRepository.setQuantityProduct(10,25L);
    }
}
