-- Crear BBDD pizzeria
CREATE DATABASE IF NOT EXISTS pizzeria;
USE pizzeria;

-- Tabla pizzas
CREATE TABLE pizzas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255),
  precio DECIMAL(6,2) NOT NULL,
  disponible BOOLEAN DEFAULT TRUE
);

--  datos de ejemplo en pizzas
INSERT INTO pizzas (nombre, descripcion, precio, disponible) VALUES
('Margarita', 'Tomate, mozzarella y albahaca', 8.50, TRUE),
('Barbacoa', 'Carne, bacon, salsa barbacoa y mozzarella', 10.00, FALSE),
('Cuatro Quesos', 'Mozzarella, gorgonzola, parmesano y cheddar', 9.75, TRUE),
('Pepperoni', 'Tomate, mozzarella y pepperoni', 9.50, TRUE),
('Vegetal', 'Tomate, mozzarella, champiñones, pimiento y cebolla', 9.00, TRUE),
('Barbacoa Especial', 'Carne, bacon, cebolla y salsa barbacoa', 11.50, TRUE),
('Jamón y Queso', 'Tomate, mozzarella y jamón cocido', 8.75, FALSE),
('Carbonara', 'Nata, mozzarella, bacon y champiñones', 10.50, TRUE);