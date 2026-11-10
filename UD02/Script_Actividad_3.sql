USE empresa_ventas;

/* Actividad 3 - Ejercicio 1*/

DROP PROCEDURE IF EXISTS total_empleados;

DELIMITER //
CREATE PROCEDURE total_empleados()
BEGIN
	SELECT COUNT(*) AS total FROM empleados;
END //
DELIMITER ;
 
/* Actividad 3 - Ejercicio 2*/
 
DROP PROCEDURE IF EXISTS salarioMedio;
DELIMITER //
CREATE PROCEDURE salarioMedio(
	IN numDepartamento INT,
    OUT media DECIMAL(10,2)
)
BEGIN
	SELECT AVG(salario)
    INTO media
    FROM empleados
    WHERE departamento_id = numDepartamento;
END //
DELIMITER ;

/* Actividad 3 - Ejercicio 3*/

DELIMITER //

CREATE PROCEDURE incrementar_salario(INOUT p_salario DOUBLE)
BEGIN
	SET p_salario = p_salario * 1.05;
END //

DELIMITER ;
