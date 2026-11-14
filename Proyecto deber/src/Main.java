import java.util.Scanner;

record Producto(String codigo, String nombre, double precio, int stock) {}

        void main() {
            Scanner scanner = new Scanner(System.in);
            int capacidadMaxima = 50;
            Producto[] productos = new Producto[capacidadMaxima];
            int cantidadProductos = 0;
            int opcion = 0;

            System.out.println("=========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE INVENTARIO - PUCE");
            System.out.println("=========================================");

            do {
                System.out.println("\n--- MENÚ PRINCIPAL ---");
                System.out.println("1. Registrar nuevo producto");
                System.out.println("2. Mostrar todos los productos");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println(" Error: Debe ingresar un número entero válido.");
                    scanner.nextLine();
                    continue;
                }

                switch (opcion) {
                    case 1 -> {
                        if (cantidadProductos >= capacidadMaxima) {
                            System.out.println("️ El inventario ha alcanzado su capacidad máxima.");
                            break;
                        }

                        System.out.println("\n--- REGISTRO DE NUEVO PRODUCTO ---");

                        String codigo;
                        while (true) {
                            System.out.print("Ingrese código del producto: ");
                            codigo = scanner.nextLine().trim();
                            if (!codigo.isEmpty()) break;
                            System.out.println(" El código no puede estar vacío.");
                        }

                        String nombre;
                        while (true) {
                            System.out.print("Ingrese nombre del producto: ");
                            nombre = scanner.nextLine().trim();
                            if (!nombre.isEmpty()) break;
                            System.out.println(" El nombre no puede estar vacío.");
                        }

                        double precio = -1;
                        while (precio < 0) {
                            System.out.print("Ingrese precio unitario ($): ");
                            if (scanner.hasNextDouble()) {
                                precio = scanner.nextDouble();
                                if (precio < 0) {
                                    System.out.println(" El precio no puede ser negativo.");
                                }
                            } else {
                                System.out.println(" Valor inválido. Ingrese un número.");
                            }
                            scanner.nextLine();
                        }

                        int stock = -1;
                        while (stock < 0) {
                            System.out.print("Ingrese cantidad en stock: ");
                            if (scanner.hasNextInt()) {
                                stock = scanner.nextInt();
                                if (stock < 0) {
                                    System.out.println("El stock no puede ser negativo.");
                                }
                            } else {
                                System.out.println(" Valor inválido. Ingrese un número entero.");
                            }
                            scanner.nextLine();
                        }

                        productos[cantidadProductos] = new Producto(codigo, nombre, precio, stock);
                        cantidadProductos++;
                        System.out.println(" ¡Producto registrado exitosamente!");
                    }
                    case 2 -> {
                        if (cantidadProductos == 0) {
                            System.out.println(" No hay productos registrados en el inventario.");
                        } else {
                            System.out.println("\n========================================");
                            System.out.println("         LISTA DE INVENTARIO            ");
                            System.out.println("========================================");
                            for (int i = 0; i < cantidadProductos; i++) {
                                Producto p = productos[i];
                                System.out.println("[%d] Código: %s | Nombre: %s | Precio: $%.2f | Stock: %d unidades".formatted(
                                        i + 1, p.codigo(), p.nombre(), p.precio(), p.stock()
                                ));
                            }
                            System.out.println("========================================");
                        }
                    }
                    case 3 -> System.out.println(" Saliendo del programa. ¡Hasta pronto!");
                    default -> System.out.println(" Opción inválida. Por favor, seleccione una opción entre 1 y 3.");
                }
            } while (opcion != 3);

            scanner.close();
        }
