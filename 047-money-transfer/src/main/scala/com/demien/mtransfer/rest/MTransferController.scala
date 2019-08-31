package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.MTransfer
import com.demien.mtransfer.service.MTransferService

object MTransferController {
  val PATH = "/api/mtransfer"
}

class MTransferController(val mTransferService: MTransferService)
  extends Controller[MTransfer](MTransferController.PATH, mTransferService, new MTransfer(1, 1, 1).getClass) {

}
