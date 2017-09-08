package io.dj.modules.job.service

import io.dj.modules.goods.domain.VersionLibraryDomain
import io.dj.modules.job.bean.CrmVersionLibraryBean
import java.util.ArrayList

interface CrmVersionLibraryService {
    fun getCrmVersionLibrary(id: String): ArrayList<CrmVersionLibraryBean>
    fun updateCrmVersionLibrary()
    fun setSave(domain: VersionLibraryDomain, bean: CrmVersionLibraryBean, isEq: Boolean)
}