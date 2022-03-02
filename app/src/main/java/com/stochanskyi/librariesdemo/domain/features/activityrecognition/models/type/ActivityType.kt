package com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.type

interface ActivityType {
    fun <T> transform(transformer: ActivityTypeTransformer<T>): T
}

class ActivityTypeInVehicle : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformInVehicle()
    }
}

class ActivityOnBicycle : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformOnBicycle()
    }
}

class ActivityOnFoot : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformOnFoot()
    }
}

class ActivityStill : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformStill()
    }
}

class ActivityUnknown : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformUnknown()
    }
}

class ActivityTilting : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformTilting()
    }
}

class ActivityWalking : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
        return transformer.transformWalking()
    }
}

class ActivityRunning : ActivityType {
    override fun <T> transform(transformer: ActivityTypeTransformer<T>): T {
       return transformer.transformRunning()
    }
}