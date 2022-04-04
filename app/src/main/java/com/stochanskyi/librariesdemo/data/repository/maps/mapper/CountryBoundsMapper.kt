package com.stochanskyi.librariesdemo.data.repository.maps.mapper

import com.stochanskyi.librariesdemo.data.maps.models.*
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.CountryBound
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.BoundsGeometry
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.Coordinate
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.MultiPolygon
import com.stochanskyi.librariesdemo.domain.features.maps.models.country.geometry.Polygon
import javax.inject.Inject

interface CountryBoundsMapper {
    fun mapCountriesBounds(dto: CountriesBoundsDto): List<CountryBound>
    fun mapCountryBound(dto: CountriesBoundsDto.CountryBoundDto): CountryBound

    fun mapGeometry(dto: BoundGeometryDto): BoundsGeometry

    fun mapMultiPolygon(dto: BoundGeometryDto.MultiPolygonDto): MultiPolygon
    fun mapPolygon(dto: BoundGeometryDto.PolygonDto): Polygon
    fun mapCoordinate(data: CoordinateData): Coordinate
}

class CountryBoundsMapperImpl @Inject constructor() : CountryBoundsMapper {

    override fun mapCountriesBounds(dto: CountriesBoundsDto): List<CountryBound> {
        return dto.features.map { mapCountryBound(it) }
    }

    override fun mapCountryBound(
        dto: CountriesBoundsDto.CountryBoundDto
    ) = with(dto) {
        CountryBound(
            countryCode = properties.countryCode,
            geometry = mapGeometry(geometry)
        )
    }

    override fun mapGeometry(dto: BoundGeometryDto): BoundsGeometry {
        return when (dto) {
            is BoundGeometryDto.PolygonDto -> mapPolygon(dto)
            is BoundGeometryDto.MultiPolygonDto -> mapMultiPolygon(dto)
        }
    }

    override fun mapMultiPolygon(dto: BoundGeometryDto.MultiPolygonDto): MultiPolygon {
        return MultiPolygon(dto.coordinates.map { mapPolygon(it) })
    }

    override fun mapPolygon(dto: BoundGeometryDto.PolygonDto): Polygon {
        return mapPolygon(dto.coordinates)
    }

    override fun mapCoordinate(data: CoordinateData): Coordinate {
        return Coordinate(
            long = data.first(),
            lat = data.last()
        )
    }

    private fun mapPolygon(data: PolygonData): Polygon {
        val mainPolygonData = data.firstOrNull() ?: emptyList()
        val holesData = data.drop(1)

        return Polygon(
            coordinates = mapCoordinates(mainPolygonData),
            holes = holesData.map { mapCoordinates(it) }
        )
    }

    private fun mapCoordinates(data: List<CoordinateData>): List<Coordinate> {
        return data.map { mapCoordinate(it) }
    }

}
