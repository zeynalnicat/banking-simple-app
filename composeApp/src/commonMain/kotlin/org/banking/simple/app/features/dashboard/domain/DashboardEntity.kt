package org.banking.simple.app.features.dashboard.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dashboard")
data class DashboardEntity(
  @PrimaryKey(autoGenerate = true)
  var id :Int = 0,
)