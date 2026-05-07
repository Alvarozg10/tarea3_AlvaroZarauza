-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-05-2026 a las 03:15:08
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
(2, 'ACROBACIA'),
(2, 'HUMOR'),
(2, 'MAGIA'),
(4, 'MAGIA'),
(3, 'MAGIA'),
(3, 'EQUILIBRISMO'),
(5, 'HUMOR'),
(5, 'MAGIA'),
(5, 'EQUILIBRISMO'),
(9, 'MAGIA'),
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
(1, '1234', 'COORDINACION', 'marta', 1),
(2, '1234', 'ARTISTA', 'roberto', 2),
(3, '1234', 'ARTISTA', 'mario', 3),
(4, '1234', 'ARTISTA', 'pedro', 4),
(5, '1234', 'ARTISTA', 'manuel', 5),
(6, '1234', 'COORDINACION', 'ramon', 6),
(7, '1234', 'COORDINACION', 'pepe', 7),
(8, '1234', 'COORDINACION', 'jannik', 8),
(9, '1234', 'ARTISTA', 'thomas', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculo`
--

CREATE TABLE `espectaculo` (
  `id` bigint(20) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `coordinador_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `espectaculo`
--

INSERT INTO `espectaculo` (`id`, `fecha_fin`, `fecha_inicio`, `nombre`, `coordinador_id`) VALUES
(1, '2026-06-09', '2026-05-07', 'Circo Fantasia', 1),
(2, '2026-06-01', '2026-05-07', 'Circo del Sol', 6),
(3, '2026-06-15', '2026-05-07', 'Circo de la Torre', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero`
--

CREATE TABLE `numero` (
  `id` bigint(20) NOT NULL,
  `duracion` double NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `orden` int(11) NOT NULL,
  `espectaculo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `numero`
--

INSERT INTO `numero` (`id`, `duracion`, `nombre`, `orden`, `espectaculo_id`) VALUES
(1, 3, 'Acrobacias', 1, 1),
(2, 1, 'Saltos', 2, 1),
(3, 2.5, 'Parkour', 3, 1),
(4, 4, 'Prueba Admin', 4, 1),
(5, 2.5, 'Saltos de pertiga', 1, 2),
(6, 1.5, 'Cuerda floja', 2, 2),
(7, 3, 'Malabares con fuego', 3, 2),
(8, 3, 'Inclinaciones', 1, 3),
(9, 2.5, 'Volteretas', 2, 3),
(10, 4, 'Saltos mortales', 3, 3);

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
(1, 3),
(2, 2),
(2, 3),
(3, 2),
(4, 3),
(6, 2),
(6, 3),
(6, 4),
(7, 2),
(7, 3),
(8, 2),
(8, 3),
(8, 4),
(8, 5),
(9, 4),
(9, 5),
(10, 3),
(10, 4),
(5, 2),
(5, 4);

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
('COORDINACION', 1, 'marta@test.com', 'Alemania', 'Marta', NULL, '2026-05-07', b'1'),
('ARTISTA', 2, 'roberto@test.com', 'Argentina', 'Roberto', 'Robert', NULL, NULL),
('ARTISTA', 3, 'mario@test.com', 'España', 'Mario', '', NULL, NULL),
('ARTISTA', 4, 'pedro@test.com', 'España', 'Perdro', '', NULL, NULL),
('ARTISTA', 5, 'manuel@test.com', 'Andorra', 'Manuel', 'Manu', NULL, NULL),
('COORDINACION', 6, 'ramon@test.com', 'Arabia Saudí', 'Ramon', NULL, '2026-05-07', b'1'),
('COORDINACION', 7, 'pepe@test.com', 'Andorra', 'Pepe', NULL, '2026-05-07', b'1'),
('COORDINACION', 8, 'jannik@test.com', 'Italia', 'Jannik', NULL, NULL, b'0'),
('ARTISTA', 9, 'thomas@test.com', 'Francia', 'Thomas', 'Tommy', NULL, NULL);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `numero`
--
ALTER TABLE `numero`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
