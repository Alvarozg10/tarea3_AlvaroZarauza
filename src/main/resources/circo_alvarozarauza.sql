-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-04-2026 a las 16:39:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `circo_alvarozarauza`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista_especialidad`
--

CREATE TABLE `artista_especialidad` (
  `artista_id` bigint(20) NOT NULL,
  `especialidad` enum('ACROBACIA','EQUILIBRISMO','HUMOR','MAGIA','MALABARISMO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artista_especialidad`
--

INSERT INTO `artista_especialidad` (`artista_id`, `especialidad`) VALUES
(12, 'HUMOR'),
(9, 'HUMOR'),
(9, 'MALABARISMO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `perfil` enum('ADMIN','ARTISTA','COORDINACION') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `persona_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `password`, `perfil`, `username`, `persona_id`) VALUES
(3, 'admin', 'ADMIN', 'admin', 7),
(5, '1234', 'ARTISTA', 'juan', 9),
(6, '1234', 'COORDINACION', 'marta', 10),
(7, 'prueba', 'COORDINACION', 'prueba', 11),
(8, '1234', 'ARTISTA', 'roberto', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculo`
--

CREATE TABLE `espectaculo` (
  `id` bigint(20) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre` varchar(25) NOT NULL,
  `coordinador_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `espectaculo`
--

INSERT INTO `espectaculo` (`id`, `fecha_fin`, `fecha_inicio`, `nombre`, `coordinador_id`) VALUES
(1, '2026-04-26', '2026-03-26', 'Gran Circo Nocturno', 11),
(2, '2026-04-25', '2026-03-26', 'Circo Estelar', 10),
(3, '2026-04-02', '2026-03-26', 'Circo Primavera', 10),
(4, '2026-04-26', '2026-03-26', 'Circo Test', 10),
(5, '2026-04-26', '2026-03-26', 'Circo Test2', 10),
(6, '2026-04-20', '2026-03-26', 'Circo Test3', 10),
(7, '2026-04-20', '2026-03-28', 'Circo Test4', 11),
(8, '2026-04-08', '2026-03-29', 'Circo Test5', 10),
(9, '2026-04-30', '2026-04-21', 'Prueba de numeros', 11),
(10, '2026-05-20', '2026-04-23', 'Prueba de numeros 2', 10),
(11, '2026-04-28', '2026-04-21', 'Prueba de estética', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero`
--

CREATE TABLE `numero` (
  `id` bigint(20) NOT NULL,
  `duracion` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `espectaculo_id` bigint(20) DEFAULT NULL,
  `orden` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `numero`
--

INSERT INTO `numero` (`id`, `duracion`, `nombre`, `espectaculo_id`, `orden`) VALUES
(1, 5.5, 'Numero1 modificado', 6, 1),
(2, 1.5, 'Numero2', 6, 2),
(3, 1.5, 'Numero3', 6, 3),
(4, 2.5, 'Numero prueba1', 7, 1),
(5, 1.5, 'Numero prueba2', 7, 2),
(6, 4.5, 'Numero prueba3', 7, 3),
(7, 1, 'Numero Test1', 8, 1),
(8, 1.5, 'Numero Test2', 8, 2),
(9, 5.5, 'Numero Test3', 8, 3),
(10, 1, 'N1', 10, 1),
(11, 2, 'N2', 10, 2),
(12, 3, 'N3', 10, 3),
(13, 1, 'num1', 11, 1),
(14, 2, 'num2', 11, 2),
(15, 3, 'num3', 11, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero_artista`
--

CREATE TABLE `numero_artista` (
  `numero_id` bigint(20) NOT NULL,
  `artista_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `numero_artista`
--

INSERT INTO `numero_artista` (`numero_id`, `artista_id`) VALUES
(2, 9),
(3, 9),
(4, 9),
(5, 9),
(6, 9),
(7, 9),
(8, 9),
(9, 9),
(1, 9),
(10, 9),
(11, 9),
(12, 9),
(12, 12),
(13, 9),
(14, 9),
(15, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `apodo` varchar(255) DEFAULT NULL,
  `fecha_senior` date DEFAULT NULL,
  `senior` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`dtype`, `id`, `email`, `nacionalidad`, `nombre`, `apodo`, `fecha_senior`, `senior`) VALUES
('COORDINACION', 7, 'admin@circo.com', 'España', 'Administrador', NULL, NULL, b'0'),
('ARTISTA', 9, 'juan@test.com', 'España', 'Juan Martínez', 'El Cuerdo', NULL, NULL),
('COORDINACION', 10, 'marta@test.com', 'España', 'Marta López', NULL, '2026-01-14', b'1'),
('COORDINACION', 11, 'prueba@educastur.es', 'España', 'prueba', NULL, '2026-03-09', b'1'),
('ARTISTA', 12, 'roberto@test.com', 'España', 'Roberto', 'El Inteligente', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista_especialidad`
--
ALTER TABLE `artista_especialidad`
  ADD KEY `FK16trwyw5q9kfjv7k0oca1bc2b` (`artista_id`);

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrd3009qatymdhoeyl8x7x245o` (`username`),
  ADD UNIQUE KEY `UK3kd2kgw099okafq78kw0j5v8e` (`persona_id`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKj0sbtxa8ch0qn1nk58qtliibw` (`nombre`),
  ADD KEY `FKtrswgemtwwffqm0131xpv1p1c` (`coordinador_id`);

--
-- Indices de la tabla `numero`
--
ALTER TABLE `numero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8btsfw0tlqxf64eucrn2maoe9` (`espectaculo_id`);

--
-- Indices de la tabla `numero_artista`
--
ALTER TABLE `numero_artista`
  ADD KEY `FKf41qxafqhu4yb4306weiwdw4j` (`artista_id`),
  ADD KEY `FKd276600pwuwqiuj887w222uai` (`numero_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbfxfxg15pmy0c1imvi6ucoeem` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `numero`
--
ALTER TABLE `numero`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artista_especialidad`
--
ALTER TABLE `artista_especialidad`
  ADD CONSTRAINT `FK16trwyw5q9kfjv7k0oca1bc2b` FOREIGN KEY (`artista_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `FKkddlmxnpsqya1eo6tt3mlrlbi` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD CONSTRAINT `FKtrswgemtwwffqm0131xpv1p1c` FOREIGN KEY (`coordinador_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `numero`
--
ALTER TABLE `numero`
  ADD CONSTRAINT `FK8btsfw0tlqxf64eucrn2maoe9` FOREIGN KEY (`espectaculo_id`) REFERENCES `espectaculo` (`id`);

--
-- Filtros para la tabla `numero_artista`
--
ALTER TABLE `numero_artista`
  ADD CONSTRAINT `FKd276600pwuwqiuj887w222uai` FOREIGN KEY (`numero_id`) REFERENCES `numero` (`id`),
  ADD CONSTRAINT `FKf41qxafqhu4yb4306weiwdw4j` FOREIGN KEY (`artista_id`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
