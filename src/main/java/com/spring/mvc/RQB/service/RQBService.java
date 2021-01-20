package com.spring.mvc.RQB.service;

import com.spring.mvc.RQB.repository.ClassifyRepository;
import com.spring.mvc.RQB.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.mvc.RQB.repository.RequestorRepository;
import com.spring.mvc.RQB.repository.ItemRepository;
import com.spring.mvc.RQB.repository.RQBRepository;

@Service
public class RQBService {
    @Autowired
    private ClassifyRepository classifyRepository;
    
    @Autowired
    private ItemRepository ItemRepository;
    
    @Autowired
    private RequestorRepository requestorRepository;
    
    @Autowired
    private RQBRepository rqbRepository;
    
    @Autowired
    private WatchRepository watchRepository;

    public ClassifyRepository getClassifyRepository() {
        return classifyRepository;
    }

    public ItemRepository getItemRepository() {
        return ItemRepository;
    }

    public RequestorRepository getRequestorRepository() {
        return requestorRepository;
    }

    public RQBRepository getRqbRepository() {
        return rqbRepository;
    }
    

    public WatchRepository getWatchRepository() {
        return watchRepository;
    }
    
    
}
