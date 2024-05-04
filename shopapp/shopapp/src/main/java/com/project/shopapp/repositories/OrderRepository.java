package com.project.shopapp.repositories;

import com.project.shopapp.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //Tìm các đơn hàng của 1 user nào đó
    List<Order> findByUserId(Long userId);
    @Query("SELECT o FROM Order o WHERE o.active = true AND (:keyword IS NULL OR :keyword = '' OR " +
            "o.fullName LIKE %:keyword% " +
            "OR o.address LIKE %:keyword% " +
            "OR o.note LIKE %:keyword% " +
            "OR o.email LIKE %:keyword%)")
    Page<Order> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
/*
INSERT INTO orders (user_id, fullname, email, phone_number, address, note, status, total_money)
VALUES
    (7, 'John Smith', 'john@example.com', '1234567890', '123 Main St', 'Note 1', 'pending', 500),
    (8, 'Eric Thompson', 'eric@example.com', '9876543210', '456 Elm St', 'Note 2', 'pending', 400),
    (8, 'Eric Thompson', 'eric@example.com', '9876543210', '456 Elm St', 'Note 2', 'pending', 400),
    (7, 'Hans', 'hans@example.com', '5555555555', '789 Oak St', 'Note 3', 'pending', 300),
    (8, 'Alice Johnson', 'alice@example.com', '5551234567', '789 Cherry Ave', 'Note 4', 'pending', 200),
    (7, 'Robert Williams', 'robert@example.com', '5559876543', '321 Maple Rd', 'Note 5', 'pending', 100),
    (7, 'Sarah Davis', 'sarah@example.com', '5554445555', '987 Elm St', 'Note 6', 'pending', 250),
    (8, 'Michael Anderson', 'michael@example.com', '5556667777', '654 Oak Ave', 'Note 7', 'pending', 350),
    (7, 'Emma Wilson', 'emma@example.com', '5558889999', '789 Maple Ln', 'Note 8', 'pending', 450),
    (7, 'Olivia Brown', 'olivia@example.com', '5551112222', '987 Pine St', 'Note 47', 'pending', 350),
    (8, 'William Davis', 'william@example.com', '5553334444', '654 Elm Ave', 'Note 48', 'pending', 250),
    (7, 'Sophia Wilson', 'sophia@example.com', '5555556666', '789 Oak Ln', 'Note 49', 'pending', 150),
    (8, 'Alexander Anderson', 'alexander@example.com', '5557778888', '456 Maple Lane', 'Note 50', 'pending', 450),
    (7, 'Ava Thompson', 'ava@example.com', '5559990000', '987 Walnut Rd', 'Note 51', 'pending', 550),
    (8, 'Daniel Johnson', 'daniel@example.com', '5552223333', '654 Pine Ave', 'Note 52', 'pending', 650),
    (7, 'Mia Williams', 'mia@example.com', '5554445555', '789 Elm St', 'Note 53', 'pending', 750),
    (8, 'James Davis', 'james@example.com', '5556667777', '456 Oak Ave', 'Note 54', 'pending', 850),
    (8, 'Benjamin Thompson', 'benjamin@example.com', '5550001111', '654 Walnut Rd', 'Note 56', 'pending', 550),
    (7, 'Sophia Anderson', 'sophia@example.com', '5551112222', '987 Pine St', 'Note 57', 'pending', 350),
    (8, 'Elijah Davis', 'elijah@example.com', '5553334444', '654 Elm Ave', 'Note 58', 'pending', 250),
    (7, 'Ava Wilson', 'ava@example.com', '5555556666', '789 Oak Ln', 'Note 59', 'pending', 150),
    (8, 'Oliver Thompson', 'oliver@example.com', '5557778888', '456 Maple Lane', 'Note 60', 'pending', 450),
    (7, 'Mia Johnson', 'mia@example.com', '5559990000', '987 Walnut Rd', 'Note 61', 'pending', 550),
    (8, 'James Williams', 'james@example.com', '5552223333', '654 Pine Ave', 'Note 62', 'pending', 650),
    (7, 'Charlotte Davis', 'charlotte@example.com', '5554445555', '789 Elm St', 'Note 63', 'pending', 750),
    (8, 'Benjamin Wilson', 'benjamin@example.com', '5556667777', '456 Oak Ave', 'Note 64', 'pending', 850),
    (7, 'Amelia Thompson', 'amelia@example.com', '5558889999', '321 Maple Ln', 'Note 65', 'pending', 950),
    (8, 'Henry Johnson', 'henry@example.com', '5550001111', '654 Walnut Rd', 'Note 66', 'pending', 550),
    (8, 'Emily Davis', 'emily@example.com', '5552223333', '456 Walnut Lane', 'Note 46', 'pending', 150);


INSERT INTO order_details (order_id, product_id, price, number_of_products, total_money, color)
VALUES
    -- 100 records with order_id from 14 to 38
    (8, 1, 10.99, 2, 21.98, 'Red'),
    (8, 2, 5.99, 3, 17.97, 'Blue'),
    (8, 3, 8.49, 1, 8.49, 'Green'),
    (9, 1, 10.99, 2, 21.98, 'Red'),
    (9, 2, 5.99, 3, 17.97, 'Blue'),
    (9, 3, 8.49, 1, 8.49, 'Green'),
    (10, 6, 12.99, 3, 38.97, 'Purple'),
    (11, 7, 6.99, 1, 6.99, 'Pink'),
    (12, 8, 14.99, 2, 29.98, 'Gray'),
    (13, 9, 11.49, 1, 11.49, 'Brown'),
    (14, 10, 8.99, 3, 26.97, 'Black'),
    (15, 11, 13.99, 2, 27.98, 'Silver'),
    (16, 12, 10.49, 1, 10.49, 'Gold'),
    (17, 13, 7.49, 2, 14.98, 'White'),
    (18, 1, 10.99, 2, 21.98, 'Red'),
    (19, 2, 5.99, 3, 17.97, 'Blue'),
    (19, 3, 8.49, 1, 8.49, 'Green'),
    (20, 14, 9.99, 2, 19.98, 'Red'),
    (20, 15, 5.99, 3, 17.97, 'Blue'),
    (20, 16, 8.49, 1, 8.49, 'Green'),
    (21, 17, 10.99, 2, 21.98, 'Yellow'),
    (21, 18, 5.99, 3, 17.97, 'Orange'),
    (21, 19, 8.49, 1, 8.49, 'Purple'),
    (22, 20, 6.99, 2, 13.98, 'Pink'),
    (22, 21, 14.99, 1, 14.99, 'Gray'),
    (22, 22, 11.49, 3, 34.47, 'Brown'),
    (23, 23, 8.99, 2, 17.98, 'Black'),
    (23, 24, 13.99, 1, 13.99, 'Silver'),
    (23, 25, 10.49, 3, 31.47, 'Gold'),
    (24, 26, 7.49, 2, 14.98, 'White'),
    (24, 27, 9.99, 1, 9.99, 'Red'),
    (24, 28, 5.99, 3, 17.97, 'Blue'),
    (25, 29, 8.49, 1, 8.49, 'Green'),
    (25, 30, 10.99, 2, 21.98, 'Yellow'),
    (25, 31, 5.99, 3, 17.97, 'Orange'),
    (26, 32, 8.49, 1, 8.49, 'Purple'),
    (26, 33, 6.99, 2, 13.98, 'Pink'),
    (26, 34, 14.99, 1, 14.99, 'Gray'),
    (27, 35, 11.49, 3, 34.47, 'Brown'),
    (28, 36, 8.99, 2, 17.98, 'Black'),
    (28, 37, 13.99, 1, 13.99, 'Silver'),
    (29, 38, 10.49, 3, 31.47, 'Gold'),
    (29, 39, 7.49, 2, 14.98, 'White'),
    (29, 40, 9.99, 1, 9.99, 'Red'),
    (30, 41, 5.99, 3, 17.97, 'Blue'),
    (30, 42, 8.49, 1, 8.49, 'Green'),
    (30, 43, 10.99, 2, 21.98, 'Yellow'),
    (31, 44, 5.99, 3, 17.97, 'Orange'),
    (31, 45, 8.49, 1, 8.49, 'Purple'),
    (31, 46, 6.99, 2, 13.98, 'Pink'),
    (32, 47, 14.99, 1, 14.99, 'Gray'),
    (32, 48, 11.49, 3, 34.47, 'Brown'),
    (32, 49, 8.99, 2, 17.98, 'Black'),
    (32, 50, 13.99, 1, 13.99, 'Silver'),
    (33, 51, 10.49, 3, 31.47, 'Gold'),
    (33, 52, 7.49, 2, 14.98, 'White'),
    (33, 53, 9.99, 1, 9.99, 'Red'),
    (33, 54, 5.99, 3, 17.97, 'Blue');

* */
