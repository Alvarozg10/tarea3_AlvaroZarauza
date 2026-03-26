-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-03-2026 a las 18:34:34
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
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `id` bigint(20) NOT NULL,
  `apodo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista_especialidad`
--

CREATE TABLE `artista_especialidad` (
  `artista_id` bigint(20) NOT NULL,
  `especialidad` enum('ACROBACIA','HUMOR','MAGIA','EQUILIBRISMO','MALABARISMO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordinacion`
--

CREATE TABLE `coordinacion` (
  `id` bigint(20) NOT NULL,
  `senior` tinyint(1) DEFAULT 0,
  `fecha_senior` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `perfil` enum('ADMIN','COORDINACION','ARTISTA') NOT NULL,
  `persona_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculo`
--

CREATE TABLE `espectaculo` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `coordinador_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero`
--

CREATE TABLE `numero` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `orden_numero` int(11) NOT NULL,
  `duracion` double DEFAULT NULL,
  `espectaculo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numero_artista`
--

CREATE TABLE `numero_artista` (
  `numero_id` bigint(20) NOT NULL,
  `artista_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `artista_especialidad`
--
ALTER TABLE `artista_especialidad`
  ADD PRIMARY KEY (`artista_id`,`especialidad`);

--
-- Indices de la tabla `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `coordinador_id` (`coordinador_id`);

--
-- Indices de la tabla `numero`
--
ALTER TABLE `numero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `espectaculo_id` (`espectaculo_id`);

--
-- Indices de la tabla `numero_artista`
--
ALTER TABLE `numero_artista`
  ADD PRIMARY KEY (`numero_id`,`artista_id`),
  ADD KEY `artista_id` (`artista_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `numero`
--
ALTER TABLE `numero`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artista`
--
ALTER TABLE `artista`
  ADD CONSTRAINT `artista_ibfk_1` FOREIGN KEY (`id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `artista_especialidad`
--
ALTER TABLE `artista_especialidad`
  ADD CONSTRAINT `artista_especialidad_ibfk_1` FOREIGN KEY (`artista_id`) REFERENCES `artista` (`id`);

--
-- Filtros para la tabla `coordinacion`
--
ALTER TABLE `coordinacion`
  ADD CONSTRAINT `coordinacion_ibfk_1` FOREIGN KEY (`id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `credenciales_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `espectaculo`
--
ALTER TABLE `espectaculo`
  ADD CONSTRAINT `espectaculo_ibfk_1` FOREIGN KEY (`coordinador_id`) REFERENCES `coordinacion` (`id`);

--
-- Filtros para la tabla `numero`
--
ALTER TABLE `numero`
  ADD CONSTRAINT `numero_ibfk_1` FOREIGN KEY (`espectaculo_id`) REFERENCES `espectaculo` (`id`);

--
-- Filtros para la tabla `numero_artista`
--
ALTER TABLE `numero_artista`
  ADD CONSTRAINT `numero_artista_ibfk_1` FOREIGN KEY (`numero_id`) REFERENCES `numero` (`id`),
  ADD CONSTRAINT `numero_artista_ibfk_2` FOREIGN KEY (`artista_id`) REFERENCES `artista` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
