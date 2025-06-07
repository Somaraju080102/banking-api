package com.spring.api.dto;

import java.time.LocalDateTime;

public class ConsentResponseDTO {
	
	 	private Long id;
	    private String dataType;
	    private boolean granted;
	    private LocalDateTime createdAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public boolean isGranted() {
			return granted;
		}
		public void setGranted(boolean granted) {
			this.granted = granted;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	    
	    

}
