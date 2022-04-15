package br.com.dg.panteao.theia.util

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord
import java.io.InputStreamReader
import kotlin.reflect.KClass

class CSVHelper

inline fun <reified T> parseCSV(csvFormat: CSVFormat, reader: InputStreamReader): List<T> =
    CSVFormat
        .Builder
        .create(csvFormat)
        .setHeader(*getHeaders<T>().toTypedArray())
        .build()
        .parse(reader)
        .map(::parseObject)

inline fun <reified T> parseObject(record: CSVRecord): T {
    val clazz = instantiateObject<T>()

    fillObject(clazz, record)

    return clazz
}

inline fun <reified T> fillObject(clazz: T, record: CSVRecord) {
    T::class.java.declaredFields.forEach { field ->
        field.getAnnotation(CSVHeader::class.java)?.let { csvHeader ->
            field.isAccessible = true
            field.set(clazz, record.get(csvHeader.header))
        }
    }
//            T::class.declaredMembers.forEach { member ->
//
//
//                member.annotations
//                member.findAnnotation<CSVHeader>()?.let { csvHeader ->
//                    member as KMutableProperty
//                    member.setter.call(clazz, record.get(csvHeader.header))
//                }
//            }

//            val clazz = T::class.java.getDeclaredConstructor().newInstance()
}

inline fun <reified T> instantiateObject(): T {


    val constructor = T::class.constructors.first()

    val arguments = constructor.parameters.map { parameter ->
        parameter.type.classifier as KClass<*>
    }.map { parameter ->
        parameter.constructors.first { constructor -> constructor.parameters.isEmpty() }.call()
    }.toTypedArray()

    return T::class.constructors.first().call(*arguments)
}

inline fun <reified T> getHeaders(): List<String> =
    T::class.java.declaredFields.mapNotNull { it.getAnnotation(CSVHeader::class.java)?.header }

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class CSVHeader(val header: String)
