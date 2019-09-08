package it.polito.ai.project.controllers;

import it.polito.ai.project.exceptions.BadRequestException;
import it.polito.ai.project.exceptions.InternalServerErrorException;
import it.polito.ai.project.exceptions.ResourceNotFoundException;
import it.polito.ai.project.exceptions.UnauthorizedRequestException;
import it.polito.ai.project.generalmodels.ClientCompanion;
import it.polito.ai.project.generalmodels.ClientRace;
import it.polito.ai.project.generalmodels.ClientUserCredentials;
import it.polito.ai.project.requestEntities.MakeCompanionRequest;
import it.polito.ai.project.requestEntities.MakeOrRemoveAdminRequest;
import it.polito.ai.project.requestEntities.SelectCompanionRequest;
import it.polito.ai.project.requestEntities.AddChildToLineRequest;
import it.polito.ai.project.services.database.DatabaseService;
import it.polito.ai.project.services.database.models.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DatabaseService db;

    @RequestMapping(value="/makeAdmin", method = RequestMethod.PUT)
    public ResponseEntity makeAdmin(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody MakeOrRemoveAdminRequest makeOrRemoveAdminRequest)
    {
        try
        {
            db.makeLineAdmin(performerUserCredentials.getUsername(), makeOrRemoveAdminRequest.getTargetName(), makeOrRemoveAdminRequest.getLineName());
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, re.getMessage());
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ue.getMessage());
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value="/removeAdmin", method = RequestMethod.PUT)
    public ResponseEntity removeAdmin(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody MakeOrRemoveAdminRequest makeOrRemoveAdminRequest)
    {
        try
        {
            db.removeLineAdmin(performerUserCredentials.getUsername(), makeOrRemoveAdminRequest.getTargetName(), makeOrRemoveAdminRequest.getLineName());
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, re.getMessage());
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ie.getMessage());
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, be.getMessage());
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ue.getMessage());
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @RequestMapping(value="/makeCompanion", method = RequestMethod.PUT)
    public ResponseEntity makeCompanion(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody MakeCompanionRequest companionRequest)
    {
        try
        {
            db.makeCompanion(performerUserCredentials.getUsername(), companionRequest.getTargetName() );
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value="/removeCompanion", method = RequestMethod.PUT)
    public ResponseEntity removeCompanion(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody ClientCompanion clientCompanion)
    {
        try
        {
            db.removeCompanion(performerUserCredentials.getUsername(), clientCompanion );
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value="/selectCompanions", method = RequestMethod.PUT)
    public ResponseEntity selectCompanions(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody SelectCompanionRequest selectCompanionRequest)
    {
        try
        {
            db.selectCompanion(performerUserCredentials.getUsername(), selectCompanionRequest.getClientRace(), selectCompanionRequest.getCompanions());
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/unselectCompanions", method = RequestMethod.PUT)
    public ResponseEntity unselectCompanions(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody ClientRace clientRace)
    {
        try
        {
            db.unselectCompanions(performerUserCredentials.getUsername(), clientRace);
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/validCompanions", method = RequestMethod.PUT)
    public ResponseEntity validCompanions(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody ClientRace clientRace)
    {
        try
        {
            db.validCompanions(performerUserCredentials.getUsername(), clientRace);
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value="/addChildrenToLine", method = RequestMethod.PUT)
    public ResponseEntity addChildrenToLine(@AuthenticationPrincipal UserCredentials performerUserCredentials, @RequestBody AddChildToLineRequest addChildToLineRequest)
    {
        try
        {
            db.addChildToLine(performerUserCredentials.getUsername(), addChildToLineRequest.getChild(), addChildToLineRequest.getLineName(), performerUserCredentials.getRoles());
            return ok(HttpStatus.OK);
        }
        catch(ResourceNotFoundException re)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch(InternalServerErrorException ie)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(BadRequestException be)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch(UnauthorizedRequestException ue)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
